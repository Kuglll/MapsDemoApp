package com.example.mapsdemoapp.ui.shared.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun <Event : Any> BaseViewModel<*, Event>.collectEvents(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    eventsHandler: (suspend (event: Event) -> Unit)
) {
    val eventsFlow = events
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(eventsFlow, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            eventsFlow.collect { eventsHandler(it) }
        }
    }
}
