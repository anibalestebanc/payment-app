package com.example.android.payment.domain.usecase

import com.example.android.payment.factory.BankFactory
import com.example.android.payment.fake.FakePaymentRepository
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class GetBanksUseCaseTest {
    private val fakeRepository = FakePaymentRepository()
    private val useCase = GetBanksUseCase(fakeRepository)

    @Test
    fun `given banks, when call to invoke, then return domain bank list`() =
        runBlocking {
            val domainBankList = BankFactory.makeDomainBankList(5)
            val paymentMethodId = RandomFactory.generateString()
            fakeRepository.domainBanks = domainBankList
            useCase.invoke(paymentMethodId).collect {
                assertEquals(domainBankList, it)
            }
        }
}