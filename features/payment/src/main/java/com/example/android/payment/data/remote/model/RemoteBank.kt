package com.example.android.payment.data.remote.model

data class RemoteBank(
    val id: String,
    val name: String,
    val status: String,
    val processing_mode: String,
    val secure_thumbnail: String,
    val thumbnail: String
)