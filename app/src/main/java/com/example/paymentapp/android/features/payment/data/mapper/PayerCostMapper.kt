package com.example.paymentapp.android.features.payment.data.mapper

import com.example.paymentapp.android.features.payment.data.remote.model.RemotePayerCost
import com.example.paymentapp.android.features.payment.domain.model.DomainPayerCost
import javax.inject.Inject

class PayerCostMapper @Inject constructor() {

    fun RemotePayerCost.asDomainPayerCost() = DomainPayerCost(
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
        total_amount = total_amount
    )
}