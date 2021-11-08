package com.example.paymentapp.android.features.payment.data.source

import com.example.paymentapp.android.features.payment.data.remote.model.RemotePaymentMethod

interface PaymentRemote {
    suspend fun getPaymentMethods(): List<RemotePaymentMethod>
}
