package com.example.android.payment.data.remote

import com.example.android.payment.data.remote.api.PaymentApi
import com.example.android.payment.factory.BankFactory
import com.example.android.payment.factory.InstallmentFactory
import com.example.android.payment.factory.PaymentMethodFactory
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PaymentRemoteImplTest {
    private val mockPaymentApi = mock<com.example.android.payment.data.remote.api.PaymentApi>()
    private val apiKey = RandomFactory.generateString()

    private val remote = com.example.android.payment.data.remote.PaymentRemoteImpl(
        api = mockPaymentApi,
        apiKey = apiKey
    )

    private val remotePaymentMethodList = com.example.android.payment.factory.PaymentMethodFactory.makeRemotePaymentMethodList(5)

    private val remoteBankList = com.example.android.payment.factory.BankFactory.makeRemoteBankList(5)
    private val remoteInstallmentList = com.example.android.payment.factory.InstallmentFactory.makeRemoteInstallmentList(1)

    private val paymentMethodId: String = RandomFactory.generateString()
    private val amount: Int = RandomFactory.generateInt()
    private val bankId: String = RandomFactory.generateString()

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return remote payment method List`() =
        runBlocking {
            stubPaymentApi()
            val result = remote.getPaymentMethods()
            assertEquals(remotePaymentMethodList, result)
        }

    @Test
    fun `given banks, when call to getBanks, then return remote bank list`() =
        runBlocking {
            stubPaymentApi()
            val result = remote.getBanks(paymentMethodId)
            assertEquals(remoteBankList, result)
        }

    @Test
    fun `given installments, when call to getInstallments, then return remote installment list`() =
        runBlocking {
            stubPaymentApi()
            val result = remote.getInstallments(amount, paymentMethodId, bankId)
            assertEquals(remoteInstallmentList[0], result)
        }

    private fun stubPaymentApi() = runBlocking {
        whenever(mockPaymentApi.getPaymentMethods(apiKey)).thenReturn(remotePaymentMethodList)
        whenever(mockPaymentApi.getBanks(apiKey, paymentMethodId)).thenReturn(remoteBankList)
        whenever(
            mockPaymentApi.getInstallments(
                apiKey = apiKey,
                amount = amount,
                paymentMethodId = paymentMethodId,
                bankId = bankId
            )
        ).thenReturn(
            remoteInstallmentList
        )
    }
}