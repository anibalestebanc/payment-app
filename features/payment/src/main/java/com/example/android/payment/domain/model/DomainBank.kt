package com.example.android.payment.domain.model

data class DomainBank(
    val id: String,
    val name: String,
    val status: String,
    val processing_mode: String,
    val secure_thumbnail: String,
    val thumbnail: String
)
