package com.example.paymentapp.android.features.payment.presentation.paymentmethod.model

sealed class PaymentMethodUiState {
    object DefaultState : PaymentMethodUiState()
    object LoadingState : PaymentMethodUiState()
    data class SuccessState(val paymentMethodList: List<UiPaymentMethod>) : PaymentMethodUiState()
    data class ErrorState(val error: Throwable) : PaymentMethodUiState()
}
