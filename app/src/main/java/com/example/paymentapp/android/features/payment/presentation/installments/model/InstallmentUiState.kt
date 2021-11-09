package com.example.paymentapp.android.features.payment.presentation.installments.model

sealed class InstallmentUiState {
    object DefaultState : InstallmentUiState()
    object LoadingState : InstallmentUiState()
    data class SuccessState(val installmentList: List<UiPayerCost>) : InstallmentUiState()
    data class ErrorState(val error: Throwable) : InstallmentUiState()
}
