package com.example.mapsdemoapp.ui.shared.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Each ViewModel in the application should extend this class. The main purpose of this class is to declare, update and
 * expose stateFlow, eventFlow and commonStateFlow that are later used in Composables.
 */
abstract class BaseViewModel<State : Any, Event : Any>(val initialState: State) : ViewModel() {

    val events: Flow<Event> get() = eventsFlow.receiveAsFlow()
    val state: StateFlow<State> get() = stateFlow.asStateFlow()
    val commonState: StateFlow<CommonState> get() = commonStateFlow.asStateFlow()

    private val eventsFlow: Channel<Event> = Channel(Channel.UNLIMITED)
    private val stateFlow: MutableStateFlow<State> by lazy { MutableStateFlow(initialState) }
    private val commonStateFlow = MutableStateFlow(CommonState())

    /**
     * Used to launch a coroutine while blocking UI - Loading will not be displayed.
     */
    protected fun launch(
        block: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(Dispatchers.IO) {
        try {
            block()
        } catch (th: Throwable) {
            showError(th.message)
        }
    }

    /**
     * Used to launch a coroutine while blocking UI - Loading will be displayed.
     */
    protected fun launchWithLoading(
        block: suspend () -> Unit,
    ): Job = viewModelScope.launch(Dispatchers.IO) {
        try {
            showLoading()
            block()
        }catch (th: Throwable) {
            showError(th.message)
        } finally {
            hideLoading()
        }
    }

    //TODO: Remove if events are not needed
    protected fun postEvent(event: Event) {
        eventsFlow.trySend(event)
    }

    protected fun updateState(updater: (State) -> State) {
        stateFlow.update(updater)
    }

    protected fun showLoading() {
        commonStateFlow.value = commonStateFlow.value.copy(loadingState = LoadingState.Loading)
    }

    protected fun hideLoading() {
        commonStateFlow.value = commonStateFlow.value.copy(loadingState = LoadingState.Idle)
    }

    protected fun showError(message: String?) {
        if (message.isNullOrBlank()) {
            showUnknownError()
        } else {
            commonStateFlow.value = commonStateFlow.value.copy(errorState = ErrorState.Error(message))
        }
    }

    protected fun showUnknownError() {
        commonStateFlow.value = commonStateFlow.value.copy(errorState = ErrorState.UnknownError)
    }

    fun dismissError() {
        commonStateFlow.value = commonStateFlow.value.copy(errorState = ErrorState.NoError)
    }

}
