package com.example.android.payment.domain.usecase

import com.example.android.payment.domain.model.DomainPaymentMethod
import com.example.android.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPaymentMethodsUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    fun invoke(): Flow<List<DomainPaymentMethod>> = repository.getPaymentMethods()
}