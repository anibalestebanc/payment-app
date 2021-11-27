package com.example.android.payment.data.remote

import com.example.android.payment.data.remote.api.PaymentApi
import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemoteInstallment
import com.example.android.payment.data.remote.model.RemotePaymentMethod
import com.example.android.payment.data.source.PaymentRemote
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PaymentRemoteImpl @Inject constructor(
    private val api: PaymentApi,
    private val apiKey: String
) : PaymentRemote {

    override suspend fun getPaymentMethods(): List<RemotePaymentMethod> =
        with(Dispatchers.IO) {
            api.getPaymentMethods(apiKey)
        }

    override suspend fun getBanks(paymentMethodId: String): List<RemoteBank> =
        with(Dispatchers.IO) {
            api.getBanks(apiKey, paymentMethodId)
        }

    override suspend fun getInstallments(
        amount: Int, paymentMethodId: String, bankId: String
    ): RemoteInstallment = with(Dispatchers.IO) {
        val response = api.getInstallments(
            apiKey = apiKey,
            amount = amount,
            paymentMethodId = paymentMethodId,
            bankId = bankId
        )
        if (response.isEmpty()) throw Exception()
        response[0]
    }
}