package com.example.paymentapp.android.features.payment.data

import com.example.paymentapp.android.features.payment.data.mapper.BankMapper
import com.example.paymentapp.android.features.payment.data.mapper.PayerCostMapper
import com.example.paymentapp.android.features.payment.data.mapper.PaymentMethodMapper
import com.example.paymentapp.android.features.payment.data.source.PaymentRemote
import com.example.paymentapp.android.features.payment.factory.BankFactory
import com.example.paymentapp.android.features.payment.factory.InstallmentFactory
import com.example.paymentapp.android.features.payment.factory.PaymentMethodFactory
import com.example.utils.android.testing.RandomFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PaymentRepositoryImplTest {
    private val mockRemote = mock<PaymentRemote>()
    private val paymentMethodMapper = PaymentMethodMapper()
    private val bankMapper = BankMapper()
    private val payerCostMapper = PayerCostMapper()
    private val repository = PaymentRepositoryImpl(
        remote = mockRemote,
        paymentMethodMapper = paymentMethodMapper,
        bankMapper = bankMapper,
        payerCostMapper = payerCostMapper
    )

    private val remotePaymentMethodList = PaymentMethodFactory.makeRemotePaymentMethodList(5)
    private val paymentMethodId: String = RandomFactory.generateString()
    private val remoteBankList = BankFactory.makeRemoteBankList(5)
    private val amount: Int = RandomFactory.generateInt()
    private val bankId: String = RandomFactory.generateString()
    private val remoteInstallment = InstallmentFactory.makeRemoteInstallment()

    @Test
    fun `given payment methods, when call to getPaymentMethods, then return domain payment methods`() =
        runBlocking {
            stubRemote()
            val result = repository.getPaymentMethods()
            assertEquals(remotePaymentMethodList.size, result.size)
        }

    @Test
    fun `given banks, when call to getBanks, then return domain bank list`() =
        runBlocking {
            stubRemote()
            val result = repository.getBanks(paymentMethodId)
            assertEquals(remoteBankList.size, result.size)
        }

    @Test
    fun `given installments, when call to getInstallments, then return domain installment list`() =
        runBlocking {
            stubRemote()
            val result = repository.getInstallments(amount, paymentMethodId, bankId)
            assertEquals(remoteInstallment.payer_costs.size, result.size)
        }

    private fun stubRemote() = runBlocking {
        whenever(mockRemote.getPaymentMethods()).thenReturn(remotePaymentMethodList)
        whenever(mockRemote.getBanks(paymentMethodId)).thenReturn(remoteBankList)
        whenever(mockRemote.getInstallments(amount, paymentMethodId, bankId)).thenReturn(remoteInstallment)
    }
}