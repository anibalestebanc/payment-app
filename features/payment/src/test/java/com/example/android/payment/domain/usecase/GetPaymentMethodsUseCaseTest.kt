package com.example.android.payment.domain.usecase

import com.example.android.payment.domain.repository.PaymentRepository
import com.example.android.payment.factory.PaymentMethodFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPaymentMethodsUseCaseTest {
    private val mockRepository = mock<com.example.android.payment.domain.repository.PaymentRepository>()
    private val useCase =
        com.example.android.payment.domain.usecase.GetPaymentMethodsUseCase(mockRepository)

    private val domainPaymentMethodList = com.example.android.payment.factory.PaymentMethodFactory.makeDomainPaymentMethodList(5)

    @Test
    fun `given payment methods, when call to invoke, then return domain payment methods`() =
        runBlocking {
            stubRepository()
            val result = useCase.invoke()
            assertEquals(domainPaymentMethodList, result)
        }

    private fun stubRepository() = runBlocking {
        whenever(mockRepository.getPaymentMethods()).thenReturn(domainPaymentMethodList)
    }
}