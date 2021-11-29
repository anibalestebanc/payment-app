package com.example.android.payment.fake

import com.example.android.payment.domain.model.DomainBank
import com.example.android.payment.domain.model.DomainPayerCost
import com.example.android.payment.domain.model.DomainPaymentMethod
import com.example.android.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePaymentRepository : PaymentRepository {
    var domainPaymentMethods: List<DomainPaymentMethod> = emptyList()
    var domainBanks: List<DomainBank> = emptyList()
    var domainPagerCosts: List<DomainPayerCost> = emptyList()

    override fun getPaymentMethods(): Flow<List<DomainPaymentMethod>> = flowOf(
        domainPaymentMethods
    )

    override fun getBanks(paymentMethodId: String): Flow<List<DomainBank>> = flowOf(
        domainBanks
    )

    override fun getInstallments(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): Flow<List<DomainPayerCost>> = flowOf(
        domainPagerCosts
    )
}