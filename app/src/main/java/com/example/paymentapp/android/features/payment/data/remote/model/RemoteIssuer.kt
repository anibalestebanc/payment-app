package com.example.paymentapp.android.features.payment.data.remote.model

data class RemoteIssuer(
    val id: String,
    val name: String,
    val secure_thumbnail: String,
    val thumbnail: String
)