package com.example.android.payment.domain.model

data class DomainPaymentMethod(
    val id: String,
    val name: String,
    val status: String,
    val accreditation_time: Int,
    val additional_info_needed: List<String>,
    val deferred_capture: String,
    val max_allowed_amount: Int,
    val min_allowed_amount: Int,
    val payment_type_id: String,
    val processing_modes: List<String>,
    val secure_thumbnail: String,
    val thumbnail: String
)
