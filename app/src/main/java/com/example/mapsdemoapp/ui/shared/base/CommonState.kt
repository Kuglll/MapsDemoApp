package com.example.mapsdemoapp.ui.shared.base

data class CommonState(
    val loadingState: LoadingState = LoadingState.Idle,
    val errorState: ErrorState = ErrorState.NoError,
)

sealed class LoadingState {

    object Idle : LoadingState()
    object Loading : LoadingState()
}

sealed class ErrorState {

    object NoError : ErrorState()
    object UnknownError : ErrorState()
    data class Error(val errorMessage: String) : ErrorState()
}
