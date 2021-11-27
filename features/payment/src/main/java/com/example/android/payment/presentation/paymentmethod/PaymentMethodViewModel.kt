package com.example.android.payment.presentation.paymentmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.payment.domain.usecase.GetPaymentMethodsUseCase
import com.example.android.payment.presentation.paymentmethod.mapper.UiPaymentMethodMapper
import com.example.android.payment.presentation.paymentmethod.model.PaymentMethodUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentMethodViewModel @Inject constructor(
    private val getPaymentMethodsUseCase: GetPaymentMethodsUseCase,
    private val mapper: UiPaymentMethodMapper
) : ViewModel() {

    private val _paymentMethodState: MutableLiveData<PaymentMethodUiState> =
        MutableLiveData(PaymentMethodUiState.DefaultState)
    val paymentMethodState: LiveData<PaymentMethodUiState> get() = _paymentMethodState

    init {
        getPaymentMethods()
    }

    fun getPaymentMethods() = viewModelScope.launch {
        _paymentMethodState.value = PaymentMethodUiState.LoadingState
        try {
            val result = getPaymentMethodsUseCase.invoke()
            val paymentMethodList = result
                .map { with(mapper) { it.asUiPaymentMethod() } }
                .sortedBy { it.name }
            _paymentMethodState.value = PaymentMethodUiState.SuccessState(paymentMethodList)
        } catch (error: Throwable) {
            _paymentMethodState.value = PaymentMethodUiState.ErrorState(error)
        }
    }
}