package com.example.android.payment.domain.repository

import com.example.android.payment.domain.model.DomainBank
import com.example.android.payment.domain.model.DomainPayerCost
import com.example.android.payment.domain.model.DomainPaymentMethod
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun getPaymentMethods(): Flow<List<DomainPaymentMethod>>
    fun getBanks(paymentMethodId: String): Flow<List<DomainBank>>
    fun getInstallments(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): Flow<List<DomainPayerCost>>
}