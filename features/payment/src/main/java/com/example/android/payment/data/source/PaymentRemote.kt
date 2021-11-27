package com.example.android.payment.data.source

import com.example.android.payment.data.remote.model.RemoteInstallment
import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemotePaymentMethod

interface PaymentRemote {
    suspend fun getPaymentMethods(): List<RemotePaymentMethod>
    suspend fun getBanks(paymentMethodId: String): List<RemoteBank>
    suspend fun getInstallments(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): RemoteInstallment
}
