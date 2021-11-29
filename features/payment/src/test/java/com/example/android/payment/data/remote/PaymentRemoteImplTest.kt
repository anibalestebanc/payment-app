package com.example.android.payment.data.remote

import com.example.android.payment.factory.BankFactory
import com.example.android.payment.factory.InstallmentFactory
import com.example.android.payment.factory.PaymentMethodFactory
import com.example.android.payment.fake.FakePaymentApi
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentRemoteImplTest {

    private val fakePaymentApi = FakePaymentApi()
    private val apiKey = RandomFactory.generateString()
    private val remote = PaymentRemoteImpl(
        api = fakePaymentApi,
        apiKey = apiKey
    )

    private val paymentMethodId: String = RandomFactory.generateString()

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return remote payment method List`() =
        runBlocking {
            val remotePaymentMethodList = PaymentMethodFactory.makeRemotePaymentMethodList(5)
            fakePaymentApi.remotePaymentMethods = remotePaymentMethodList
            remote.getPaymentMethods().collect {
                assertEquals(remotePaymentMethodList, it)
            }
        }

    @Test
    fun `given banks, when call to getBanks, then return remote bank list`() =
        runBlocking {
            val remoteBankList = BankFactory.makeRemoteBankList(5)
            fakePaymentApi.remoteBanks = remoteBankList
            remote.getBanks(paymentMethodId).collect {
                assertEquals(remoteBankList, it)
            }
        }

    @Test
    fun `given installments, when call to getInstallments, then return remote installment list`() =
        runBlocking {
            val remoteInstallmentList = InstallmentFactory.makeRemoteInstallmentList(1)
            fakePaymentApi.remoteInstallments = remoteInstallmentList
            val amount: Int = RandomFactory.generateInt()
            val bankId: String = RandomFactory.generateString()
            remote.getInstallments(amount, paymentMethodId, bankId).collect {
                assertEquals(remoteInstallmentList.first().payer_costs, it)
            }
        }
}