package com.example.paymentapp.android.features.payment.domain.usecase

import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import com.example.paymentapp.android.features.payment.factory.BankFactory
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetBanksUseCaseTest {
    private val mockRepository = mock<PaymentRepository>()
    private val useCase = GetBanksUseCase(mockRepository)

    private val paymentMethodId = RandomFactory.generateString()
    private val domainBankList = BankFactory.makeDomainBankList(5)

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