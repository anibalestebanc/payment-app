package com.example.paymentapp.android.features.payment.presentation.bank.model

sealed class BankUiState {
    object DefaultState : BankUiState()
    object LoadingState : BankUiState()
    data class SuccessState(val bakList: List<UiBank>) : BankUiState()
    data class ErrorState(val error: Throwable) : BankUiState()
}
