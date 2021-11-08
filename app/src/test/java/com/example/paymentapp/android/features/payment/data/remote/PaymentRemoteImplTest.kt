package com.example.paymentapp.android.features.payment.data.remote

import com.example.paymentapp.android.features.payment.data.remote.api.PaymentApi
import com.example.paymentapp.android.features.payment.factory.PaymentMethodFactory
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PaymentRemoteImplTest {
    private val mockPaymentApi = mock<PaymentApi>()
    private val apiKey = RandomFactory.generateString()

    private val remote = PaymentRemoteImpl(
        api = mockPaymentApi,
        apiKey = apiKey
    )

    private val remotePaymentMethodList = PaymentMethodFactory.makeRemotePaymentMethodList(5)

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return domain payment methods`() =
        runBlocking {
            stubPaymentApi()
            val result = remote.getPaymentMethods()
            assertEquals(remotePaymentMethodList, result)
        }

    private fun stubPaymentApi() = runBlocking{
          whenever(mockPaymentApi.getPaymentMethods(apiKey)).thenReturn(remotePaymentMethodList)
    }

}