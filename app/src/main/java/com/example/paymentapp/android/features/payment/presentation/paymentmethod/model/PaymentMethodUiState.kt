package com.example.paymentapp.android.features.payment.presentation.paymentmethod.model

sealed class PaymentMethodUiState {
    object DefaultState : PaymentMethodUiState()
    object LoadingState : PaymentMethodUiState()
    object SuccessState : PaymentMethodUiState()
    data class ErrorState(val error: Throwable) : PaymentMethodUiState()
}
