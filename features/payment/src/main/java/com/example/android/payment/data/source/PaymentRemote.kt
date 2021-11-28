package com.example.android.payment.data.source

import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemotePayerCost
import com.example.android.payment.data.remote.model.RemotePaymentMethod
import kotlinx.coroutines.flow.Flow

interface PaymentRemote {
    fun getPaymentMethods(): Flow<List<RemotePaymentMethod>>
    fun getBanks(paymentMethodId: String): Flow<List<RemoteBank>>
    fun getInstallments(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): Flow<List<RemotePayerCost>>
}
