package com.example.android.payment.domain.usecase

import com.example.android.payment.domain.repository.PaymentRepository
import com.example.android.payment.factory.PayerCostFactory
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetInstallmentsUseCaseTest {
    private val mockRepository = mock<com.example.android.payment.domain.repository.PaymentRepository>()
    private val useCase =
        com.example.android.payment.domain.usecase.GetInstallmentsUseCase(mockRepository)

    private val paymentMethodId = RandomFactory.generateString()
    private val amount = RandomFactory.generateInt()
    private val bankId = RandomFactory.generateString()

    private val domainPayerCostList = com.example.android.payment.factory.PayerCostFactory.makeDomainPayerCostList(5)

    @Test
    fun `given installments, when call to invoke, then return domain installments list`() =
        runBlocking {
            stubRepository()
            val result = useCase.invoke(amount, paymentMethodId, bankId)
            Assert.assertEquals(domainPayerCostList, result)
        }

    private fun stubRepository() = runBlocking {
        whenever(mockRepository.getInstallments(amount, paymentMethodId, bankId)).thenReturn(
            domainPayerCostList
        )
    }
}