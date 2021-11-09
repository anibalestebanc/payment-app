package com.example.paymentapp.android.features.payment.presentation.paymentmethod.mapper

import com.example.paymentapp.android.features.payment.domain.model.DomainPaymentMethod
import com.example.paymentapp.android.features.payment.presentation.paymentmethod.model.UiPaymentMethod
import javax.inject.Inject

class UiPaymentMethodMapper @Inject constructor() {

    fun DomainPaymentMethod.asUiPaymentMethod() = UiPaymentMethod(
        id = id,
        name = name,
        secure_thumbnail = secure_thumbnail
    )
}