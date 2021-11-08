package com.example.paymentapp.android.features.payment.presentation.paymentmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymentapp.android.features.payment.domain.usecase.GetPaymentMethodsUseCase
import com.example.paymentapp.android.features.payment.presentation.paymentmethod.model.PaymentMethodUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private val getPaymentMethodsUseCase: GetPaymentMethodsUseCase
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
        } catch (error: Throwable) {
            _paymentMethodState.value = PaymentMethodUiState.ErrorState(error)
        }
    }
}