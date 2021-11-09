package com.example.paymentapp.android.features.payment.presentation.installments.mapper

import com.example.paymentapp.android.features.payment.domain.model.DomainPayerCost
import com.example.paymentapp.android.features.payment.presentation.installments.model.UiPayerCost
import javax.inject.Inject

class UiPayerCostMapper @Inject constructor() {

    fun DomainPayerCost.asUiPayerCost() = UiPayerCost(
        discount_rate = discount_rate,
        installment_amount = installment_amount,
        installment_rate = installment_rate,
        installment_rate_collector = installment_rate_collector,
        installments = installments,
        labels = labels,
        max_allowed_amount = max_allowed_amount,
        min_allowed_amount = min_allowed_amount,
        payment_method_option_id = payment_method_option_id,
        recommended_message = recommended_message,
        total_amount = total_amount,
        isSelected = false
    )
}