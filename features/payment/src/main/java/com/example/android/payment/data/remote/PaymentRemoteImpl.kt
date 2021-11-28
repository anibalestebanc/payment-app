package com.example.android.payment.data.remote

import com.example.android.payment.data.remote.api.PaymentApi
import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemotePayerCost
import com.example.android.payment.data.remote.model.RemotePaymentMethod
import com.example.android.payment.data.source.PaymentRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PaymentRemoteImpl @Inject constructor(
    private val api: PaymentApi,
    private val apiKey: String
) : PaymentRemote {

    override fun getPaymentMethods(): Flow<List<RemotePaymentMethod>> = flow {
        val result = api.getPaymentMethods(apiKey)
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getBanks(paymentMethodId: String): Flow<List<RemoteBank>> = flow {
        emit(api.getBanks(apiKey, paymentMethodId))
    }.flowOn(Dispatchers.IO)

    override fun getInstallments(
        amount: Int, paymentMethodId: String, bankId: String
    ): Flow<List<RemotePayerCost>> = flow {
        val payerCosts = api.getInstallments(
            apiKey = apiKey,
            amount = amount,
            paymentMethodId = paymentMethodId,
            bankId = bankId
        ).firstOrNull()?.payer_costs ?: emptyList()
        emit(payerCosts)
    }.flowOn(Dispatchers.IO)
}