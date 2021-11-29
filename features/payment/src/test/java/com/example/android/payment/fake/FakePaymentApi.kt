package com.example.android.payment.fake

import com.example.android.payment.data.remote.api.PaymentApi
import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemoteInstallment
import com.example.android.payment.data.remote.model.RemotePaymentMethod

class FakePaymentApi : PaymentApi {
    var remotePaymentMethods: List<RemotePaymentMethod> = emptyList()
    var remoteBanks: List<RemoteBank> = emptyList()
    var remoteInstallments: List<RemoteInstallment> = emptyList()

    override suspend fun getPaymentMethods(apiKey: String): List<RemotePaymentMethod> {
        return remotePaymentMethods
    }

    override suspend fun getBanks(apiKey: String, paymentMethodId: String): List<RemoteBank> {
        return remoteBanks
    }

    override suspend fun getInstallments(
        apiKey: String,
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): List<RemoteInstallment> {
        return remoteInstallments
    }
}