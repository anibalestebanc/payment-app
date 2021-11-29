package com.example.android.payment.domain.usecase

import com.example.android.payment.factory.PayerCostFactory
import com.example.android.payment.fake.FakePaymentRepository
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetInstallmentsUseCaseTest {
    private val fakeRepository = FakePaymentRepository()
    private val useCase = GetInstallmentsUseCase(fakeRepository)

    @Test
    fun `given installments, when call to invoke, then return domain installments list`() =
        runBlocking {
            val domainPayerCostList = PayerCostFactory.makeDomainPayerCostList(5)
            fakeRepository.domainPagerCosts = domainPayerCostList
            val paymentMethodId = RandomFactory.generateString()
            val amount = RandomFactory.generateInt()
            val bankId = RandomFactory.generateString()
            useCase.invoke(amount, paymentMethodId, bankId).collect {
                assertEquals(domainPayerCostList, it)
            }
        }
}