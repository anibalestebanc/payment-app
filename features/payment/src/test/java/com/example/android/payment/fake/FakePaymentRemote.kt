package com.example.android.payment.fake

import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemotePayerCost
import com.example.android.payment.data.remote.model.RemotePaymentMethod
import com.example.android.payment.data.source.PaymentRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePaymentRemote : PaymentRemote {

    var remotePaymentMethods: List<RemotePaymentMethod> = emptyList()
    var remoteBanks: List<RemoteBank> = emptyList()
    var remotePagerCost: List<RemotePayerCost> = emptyList()

    override fun getPaymentMethods(): Flow<List<RemotePaymentMethod>> = flowOf(
        remotePaymentMethods
    )

    override fun getBanks(paymentMethodId: String): Flow<List<RemoteBank>> = flowOf(
        remoteBanks
    )

    override fun getInstallments(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): Flow<List<RemotePayerCost>> = flowOf(
        remotePagerCost
    )
}