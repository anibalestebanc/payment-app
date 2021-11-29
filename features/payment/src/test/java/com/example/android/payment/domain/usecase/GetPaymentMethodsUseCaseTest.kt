package com.example.android.payment.domain.usecase

import com.example.android.payment.factory.PaymentMethodFactory
import com.example.android.payment.fake.FakePaymentRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPaymentMethodsUseCaseTest {
    private val fakeRepository = FakePaymentRepository()
    private val useCase = GetPaymentMethodsUseCase(fakeRepository)

    @Test
    fun `given payment methods, when call to invoke, then return domain payment methods`() =
        runBlocking {
            val domainPaymentMethodList = PaymentMethodFactory.makeDomainPaymentMethodList(5)
            fakeRepository.domainPaymentMethods = domainPaymentMethodList
            useCase.invoke().collect {
                assertEquals(domainPaymentMethodList, it)
            }
        }
}