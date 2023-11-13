package com.example.mapsdemoapp.ui.shared.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mapsdemoapp.R
import com.example.mapsdemoapp.ui.shared.ErrorDialog
import com.example.mapsdemoapp.ui.shared.LoadingOverlay

/**
 * The purpose of this class is to observe commonState and thus handle common errors and loading state.
 * Every other screen in the application should be wrapped around with this BaseComposable.
 * @param viewModel - A ViewModel that a screen wrapped around BaseComposable is using.
 * @param statusBarAware - If the screen should have visible status bar or not.
 * @param statusBarColor - Color of the status bar.
 * @param eventsHandler - function that handles events that could be posted from ViewModel.
 * @param content - Composable function that represents the content of the screen that a BaseComposable is
 * wrapping around.
 */
@Composable
fun <State : Any, Event : Any> BaseComposable(
    viewModel: BaseViewModel<State, Event>?,
    eventsHandler: suspend (event: Event) -> Unit,
    content: @Composable (state: State) -> Unit,
) {
    viewModel?.collectEvents(eventsHandler = eventsHandler)
    BaseComposable(
        viewModel = viewModel,
        content = content,
    )
}

/**
 * The purpose of this class is to observe commonState and thus handle common errors and loading state.
 * Every other screen in the application should be wrapped around with this BaseComposable.
 * @param viewModel - A ViewModel that a screen wrapped around BaseComposable is using.
 * @param statusBarAware - If the screen should have visible status bar or not.
 * @param statusBarColor - Color of the status bar.
 * @param content - Composable function that represents the content of the screen that a BaseComposable is
 * wrapping around.
 */
@Composable
fun <State : Any, Event : Any> BaseComposable(
    viewModel: BaseViewModel<State, Event>?,
    content: @Composable (state: State) -> Unit,
) {
    val state = viewModel?.state?.collectAsStateWithLifecycle()
    val commonState = viewModel?.commonState?.collectAsStateWithLifecycle()

    state?.value?.let {
        content(it)
    }

    commonState?.value?.errorState.let {
        when (it) {
            null,
            is ErrorState.NoError -> {
                //Do nothing
            }

            is ErrorState.Error -> {
                ErrorDialog(
                    title = it.errorMessage,
                    onConfirmClick = {
                        viewModel?.dismissError()
                    },
                    onDismissRequest = {
                        viewModel?.dismissError()
                    }
                )
            }
            is ErrorState.UnknownError -> {
                ErrorDialog(
                    titleId = R.string.error,
                    onConfirmClick = {
                        viewModel?.dismissError()
                    },
                    onDismissRequest = {
                        viewModel?.dismissError()
                    }
                )
            }
        }
    }

    commonState?.value?.loadingState.let {
        when (it) {
            null,
            is LoadingState.Idle -> {
                // Do nothing
            }
            is LoadingState.Loading -> {
                LoadingOverlay()
            }
        }
    }
}
