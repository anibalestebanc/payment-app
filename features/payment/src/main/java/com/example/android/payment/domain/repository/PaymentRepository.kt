package com.example.android.payment.domain.repository

import com.example.android.payment.domain.model.DomainBank
import com.example.android.payment.domain.model.DomainPayerCost
import com.example.android.payment.domain.model.DomainPaymentMethod

interface PaymentRepository {
    suspend fun getPaymentMethods(): List<DomainPaymentMethod>
    suspend fun getBanks(paymentMethodId: String): List<DomainBank>
    suspend fun getInstallments(amount: Int, paymentMethodId: String, bankId: String): List<DomainPayerCost>
}