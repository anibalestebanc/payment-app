package com.example.paymentapp.android.features.payment.data.remote.api

import com.example.paymentapp.android.features.payment.data.remote.model.PaymentMethodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentApi {

    @GET("/v1/payment_methods")
    suspend fun getPaymentMethods(
        @Query("public_key") apiKey: String,
    ): PaymentMethodResponse
}