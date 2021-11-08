package com.example.paymentapp.android.features.payment.domain.usecase

import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import javax.inject.Inject

class GetPaymentMethodsUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    suspend fun invoke() = repository.getPaymentMethods()
}