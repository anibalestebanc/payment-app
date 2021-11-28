package com.example.android.payment.presentation.paymentmethod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.payment.domain.usecase.GetPaymentMethodsUseCase
import com.example.android.payment.presentation.paymentmethod.mapper.UiPaymentMethodMapper
import com.example.android.payment.presentation.paymentmethod.model.PaymentMethodUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentMethodViewModel @Inject constructor(
    private val getPaymentMethodsUseCase: GetPaymentMethodsUseCase,
    private val paymentMethodMapper: UiPaymentMethodMapper
) : ViewModel() {

    private val _paymentMethodState = MutableStateFlow<PaymentMethodUiState>(
        PaymentMethodUiState.DefaultState
    )
    val paymentMethodState: StateFlow<PaymentMethodUiState> get() = _paymentMethodState

    init {
        getPaymentMethods()
    }

    fun getPaymentMethods() = viewModelScope.launch {
        getPaymentMethodsUseCase.invoke()
            .onStart { _paymentMethodState.value = PaymentMethodUiState.LoadingState }
            .catch { _paymentMethodState.value = PaymentMethodUiState.ErrorState(it) }
            .collect {
                val uiPaymentMethodList = it
                    .map { with(paymentMethodMapper) { it.asUiPaymentMethod() } }
                    .sortedBy { it.name }
                _paymentMethodState.value = PaymentMethodUiState.SuccessState(uiPaymentMethodList)
            }
    }
}