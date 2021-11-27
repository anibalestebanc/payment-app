package com.example.android.payment.data.remote.api

import com.example.android.payment.data.remote.model.RemoteInstallment
import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.data.remote.model.RemotePaymentMethod
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentApi {

    @GET("/v1/payment_methods")
    suspend fun getPaymentMethods(
        @Query("public_key") apiKey: String,
    ): List<RemotePaymentMethod>

    @GET("/v1/payment_methods/card_issuers")
    suspend fun getBanks(
        @Query("public_key") apiKey: String,
        @Query("payment_method_id") paymentMethodId: String
    ): List<RemoteBank>

    @GET("/v1/payment_methods/installments")
    suspend fun getInstallments(
        @Query("public_key") apiKey: String,
        @Query("amount") amount: Int,
        @Query("payment_method_id") paymentMethodId: String,
        @Query("issuer.id") bankId: String
    ) : List<RemoteInstallment>
}