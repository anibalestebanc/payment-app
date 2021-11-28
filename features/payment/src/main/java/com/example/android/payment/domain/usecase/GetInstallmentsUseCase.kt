package com.example.android.payment.domain.usecase

import com.example.android.payment.domain.repository.PaymentRepository
import javax.inject.Inject

class GetInstallmentsUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    fun invoke(amount: Int, paymentMethodId: String, bankId: String) =
        repository.getInstallments(amount, paymentMethodId, bankId)
}