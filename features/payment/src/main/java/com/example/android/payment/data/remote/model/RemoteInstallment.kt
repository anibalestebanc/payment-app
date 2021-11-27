package com.example.android.payment.data.remote.model

data class RemoteInstallment(
    val issuer: RemoteIssuer,
    val payer_costs: List<RemotePayerCost>,
    val payment_method_id: String,
    val payment_type_id: String,
    val processing_mode: String
)