package com.example.android.payment.presentation.installments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.payment.domain.usecase.GetInstallmentsUseCase
import com.example.android.payment.presentation.installments.mapper.UiPayerCostMapper
import com.example.android.payment.presentation.installments.model.InstallmentUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstallmentsViewModel @Inject constructor(
    private val getInstallmentsUseCase: GetInstallmentsUseCase,
    private val payerCostMapper: UiPayerCostMapper
) : ViewModel() {

    private val _installmentState: MutableLiveData<InstallmentUiState> =
        MutableLiveData(InstallmentUiState.DefaultState)
    val installmentState: LiveData<InstallmentUiState> get() = _installmentState

    fun getInstallments(amount: Int, paymentMethodId: String, bankId: String) =
        viewModelScope.launch {
            _installmentState.value = InstallmentUiState.LoadingState
            try {
                val result = getInstallmentsUseCase.invoke(amount, paymentMethodId, bankId)
                val installmentList = result.map { with(payerCostMapper) { it.asUiPayerCost() } }
                _installmentState.value = InstallmentUiState.SuccessState(installmentList)
            } catch (error: Throwable) {
                _installmentState.value = InstallmentUiState.ErrorState(error)
            }
        }
}