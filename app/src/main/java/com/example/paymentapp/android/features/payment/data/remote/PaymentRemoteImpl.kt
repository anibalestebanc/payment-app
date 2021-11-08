package com.example.paymentapp.android.features.payment.data.remote

import com.example.paymentapp.android.features.payment.data.remote.api.PaymentApi
import com.example.paymentapp.android.features.payment.data.remote.model.RemotePaymentMethod
import com.example.paymentapp.android.features.payment.data.source.PaymentRemote
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PaymentRemoteImpl @Inject constructor(
    private val api: PaymentApi,
    private val apiKey: String
) : PaymentRemote {

    override suspend fun getPaymentMethods(): List<RemotePaymentMethod> = with(Dispatchers.IO) {
        api.getPaymentMethods(apiKey)
    }
}