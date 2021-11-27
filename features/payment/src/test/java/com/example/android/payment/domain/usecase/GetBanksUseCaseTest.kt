package com.example.android.payment.domain.usecase

import com.example.android.payment.domain.repository.PaymentRepository
import com.example.android.payment.factory.BankFactory
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetBanksUseCaseTest {
    private val mockRepository = mock<com.example.android.payment.domain.repository.PaymentRepository>()
    private val useCase = com.example.android.payment.domain.usecase.GetBanksUseCase(mockRepository)

    private val paymentMethodId = RandomFactory.generateString()
    private val domainBankList = com.example.android.payment.factory.BankFactory.makeDomainBankList(5)

    @Test
    fun `given banks, when call to invoke, then return domain bank list`() =
        runBlocking {
            stubRepository()
            val result = useCase.invoke(paymentMethodId)
            assertEquals(domainBankList, result)
        }

    private fun stubRepository() = runBlocking {
        whenever(mockRepository.getBanks(paymentMethodId)).thenReturn(domainBankList)
    }
}