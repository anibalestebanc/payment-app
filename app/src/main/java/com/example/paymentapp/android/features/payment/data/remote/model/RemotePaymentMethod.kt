package com.example.paymentapp.android.features.payment.data.remote.model

data class RemotePaymentMethod(
    val accreditation_time: Int,
    val additional_info_needed: List<String>,
    val deferred_capture: String,
    val id: String,
    val max_allowed_amount: Int,
    val min_allowed_amount: Int,
    val name: String,
    val payment_type_id: String,
    val processing_modes: List<String>,
    val secure_thumbnail: String,
    val status: String,
    val thumbnail: String
)