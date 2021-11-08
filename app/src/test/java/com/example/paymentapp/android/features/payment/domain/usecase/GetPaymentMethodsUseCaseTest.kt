package com.example.paymentapp.android.features.payment.domain.usecase

import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import com.example.paymentapp.android.features.payment.factory.PaymentMethodFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPaymentMethodsUseCaseTest {
    private val mockRepository = mock<PaymentRepository>()
    private val useCase = GetPaymentMethodsUseCase(mockRepository)

    private val domainPaymentMethodList = PaymentMethodFactory.makeDomainPaymentMethodList(5)

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return domain payment methods`() =
        runBlocking {
            stubRepository()
            val result = useCase.invoke()
            assertEquals(domainPaymentMethodList, result)
        }

    private fun stubRepository() = runBlocking {
        whenever(mockRepository.getPaymentMethods()).thenReturn(domainPaymentMethodList)
    }
}