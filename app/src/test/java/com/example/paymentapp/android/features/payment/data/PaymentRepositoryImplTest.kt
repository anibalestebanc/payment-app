package com.example.paymentapp.android.features.payment.data

import com.example.paymentapp.android.features.payment.data.mapper.PaymentMethodMapper
import com.example.paymentapp.android.features.payment.data.source.PaymentRemote
import com.example.paymentapp.android.features.payment.factory.PaymentMethodFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PaymentRepositoryImplTest {
    private val mockRemote = mock<PaymentRemote>()
    private val mapper = PaymentMethodMapper()
    private val repository = PaymentRepositoryImpl(
        remote = mockRemote,
        mapper = mapper
    )

    private val remotePaymentMethodList = PaymentMethodFactory.makeRemotePaymentMethodList(5)

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return domain payment methods`() =
        runBlocking {
            stubRemote()
            val result = repository.getPaymentMethods()
            assertEquals(remotePaymentMethodList.size, result.size)
        }

    private fun stubRemote() = runBlocking {
        whenever(mockRemote.getPaymentMethods()).thenReturn(remotePaymentMethodList)
    }

}