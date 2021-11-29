package com.example.android.payment.data

import com.example.android.payment.data.mapper.BankMapper
import com.example.android.payment.data.mapper.PayerCostMapper
import com.example.android.payment.data.mapper.PaymentMethodMapper
import com.example.android.payment.factory.BankFactory
import com.example.android.payment.factory.InstallmentFactory
import com.example.android.payment.factory.PaymentMethodFactory
import com.example.android.payment.fake.FakePaymentRemote
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentRepositoryImplTest {

    private val fakeRemote = FakePaymentRemote()
    private val paymentMethodMapper = PaymentMethodMapper()
    private val bankMapper = BankMapper()
    private val payerCostMapper = PayerCostMapper()

    private val repository = PaymentRepositoryImpl(
        remote = fakeRemote,
        paymentMethodMapper = paymentMethodMapper,
        bankMapper = bankMapper,
        payerCostMapper = payerCostMapper
    )

    private val paymentMethodId: String = RandomFactory.generateString()

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return domain payment methods`() =
        runBlocking {
            val remotePaymentMethodList = PaymentMethodFactory.makeRemotePaymentMethodList(5)
            fakeRemote.remotePaymentMethods = remotePaymentMethodList
            repository.getPaymentMethods().collect {
                assertEquals(remotePaymentMethodList.size, it.size)
            }
        }

    @Test
    fun `given banks, when call to getBanks, then return domain bank list`() =
        runBlocking {
            val remoteBankList = BankFactory.makeRemoteBankList(5)
            fakeRemote.remoteBanks = remoteBankList
            repository.getBanks(paymentMethodId).collect {
                assertEquals(remoteBankList.size, it.size)
            }

        }

    @Test
    fun `given installments, when call to getInstallments, then return domain installment list`() =
        runBlocking {
            val amount: Int = RandomFactory.generateInt()
            val bankId: String = RandomFactory.generateString()
            val remoteInstallment = InstallmentFactory.makeRemoteInstallment()
            fakeRemote.remotePagerCost = remoteInstallment.payer_costs
            repository.getInstallments(amount, paymentMethodId, bankId).collect {
                assertEquals(remoteInstallment.payer_costs.size, it.size)
            }
        }
}