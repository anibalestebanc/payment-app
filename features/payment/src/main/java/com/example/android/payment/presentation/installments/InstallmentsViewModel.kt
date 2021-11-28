package com.example.android.payment.presentation.installments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.payment.domain.usecase.GetInstallmentsUseCase
import com.example.android.payment.presentation.installments.mapper.UiPayerCostMapper
import com.example.android.payment.presentation.installments.model.InstallmentUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstallmentsViewModel @Inject constructor(
    private val getInstallmentsUseCase: GetInstallmentsUseCase,
    private val uiPayerCostMapper: UiPayerCostMapper
) : ViewModel() {

    private val _installmentState =
        MutableStateFlow<InstallmentUiState>(InstallmentUiState.DefaultState)
    val installmentState: StateFlow<InstallmentUiState> get() = _installmentState

    fun getInstallments(amount: Int, paymentMethodId: String, bankId: String) =
        viewModelScope.launch {
            getInstallmentsUseCase.invoke(amount, paymentMethodId, bankId)
                .onStart { _installmentState.value = InstallmentUiState.LoadingState }
                .catch { _installmentState.value = InstallmentUiState.ErrorState(it) }
                .collect { payerCostList ->
                    val installmentList =
                        payerCostList.map { with(uiPayerCostMapper) { it.asUiPayerCost() } }
                    _installmentState.value = InstallmentUiState.SuccessState(installmentList)
                }
        }
}