package com.paixao.dev.misturia.presentation.state

sealed class HomeScreenUiState {
    data class Loading(val isLoading: Boolean = false) : HomeScreenUiState()
    data class Error(val error: String) : HomeScreenUiState()
    data class Failure(val exception: Throwable) : HomeScreenUiState()

    data class Receipt(val receipt: String) : HomeScreenUiState()
}