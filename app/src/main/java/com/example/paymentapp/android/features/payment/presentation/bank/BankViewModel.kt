package com.example.paymentapp.android.features.payment.presentation.bank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymentapp.android.features.payment.domain.usecase.GetBanksUseCase
import com.example.paymentapp.android.features.payment.presentation.bank.mapper.UiBankMapper
import com.example.paymentapp.android.features.payment.presentation.bank.model.BankUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BankViewModel @Inject constructor(
    private val getBanksUseCase: GetBanksUseCase,
    private val bankMapper: UiBankMapper
) : ViewModel() {

    private val _bankState: MutableLiveData<BankUiState> = MutableLiveData(BankUiState.DefaultState)
    val bankState: LiveData<BankUiState> get() = _bankState

    fun getBanks(paymentMethodId: String) = viewModelScope.launch {
        _bankState.value = BankUiState.LoadingState
        try {
            val result = getBanksUseCase.invoke(paymentMethodId)
            val bankList = result.map { with(bankMapper) { it.asUiBank() } }
            _bankState.value = BankUiState.SuccessState(bankList)
        } catch (error: Throwable) {
            _bankState.value = BankUiState.ErrorState(error)
        }
    }
}