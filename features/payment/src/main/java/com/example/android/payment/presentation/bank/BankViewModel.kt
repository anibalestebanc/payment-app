package com.example.android.payment.presentation.bank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.payment.domain.usecase.GetBanksUseCase
import com.example.android.payment.presentation.bank.mapper.UiBankMapper
import com.example.android.payment.presentation.bank.model.BankUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class BankViewModel @Inject constructor(
    private val getBanksUseCase: GetBanksUseCase,
    private val uiBankMapper: UiBankMapper
) : ViewModel() {

    private val _bankState = MutableStateFlow<BankUiState>(BankUiState.DefaultState)
    val bankState: StateFlow<BankUiState> get() = _bankState

    fun getBanks(paymentMethodId: String) = viewModelScope.launch {
        getBanksUseCase.invoke(paymentMethodId)
            .onStart { _bankState.value = BankUiState.LoadingState }
            .catch { _bankState.value = BankUiState.ErrorState(it) }
            .collect { domainBanks ->
                val bankList = domainBanks
                    .map { with(uiBankMapper) { it.asUiBank() } }
                    .sortedBy { it.name }
                _bankState.value = BankUiState.SuccessState(bankList)
            }
    }
}