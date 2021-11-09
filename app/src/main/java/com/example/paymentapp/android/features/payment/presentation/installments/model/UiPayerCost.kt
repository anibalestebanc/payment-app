package com.example.paymentapp.android.features.payment.presentation.installments.model

data class UiPayerCost(
    val discount_rate: Int,
    val installment_amount: Float,
    val installment_rate: Float,
    val installment_rate_collector: List<String>,
    val installments: Int,
    val labels: List<String>,
    val max_allowed_amount: Int,
    val min_allowed_amount: Int,
    val payment_method_option_id: String,
    val recommended_message: String,
    val total_amount: Float,
    var isSelected: Boolean
)
