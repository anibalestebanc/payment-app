package com.example.paymentapp.android.features.payment.domain.usecase

import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import javax.inject.Inject

class GetInstallmentsUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    suspend fun invoke(
        amount: Int, paymentMethodId: String, bankId: String
    ) = repository.getInstallments(amount, paymentMethodId, bankId)
}