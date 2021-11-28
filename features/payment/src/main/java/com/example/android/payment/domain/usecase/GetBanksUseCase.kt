package com.example.android.payment.domain.usecase

import com.example.android.payment.domain.model.DomainBank
import com.example.android.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBanksUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    fun invoke(paymentMethodId: String): Flow<List<DomainBank>> =
        repository.getBanks(paymentMethodId)
}