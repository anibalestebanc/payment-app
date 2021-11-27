package com.example.android.payment.data.mapper

import com.example.android.payment.data.remote.model.RemotePaymentMethod
import com.example.android.payment.domain.model.DomainPaymentMethod
import javax.inject.Inject

class PaymentMethodMapper @Inject constructor() {

    fun RemotePaymentMethod.asDomainPaymentMethod() = DomainPaymentMethod(
        id = id,
        name = name,
        status = status,
        accreditation_time = accreditation_time,
        additional_info_needed = additional_info_needed,
        deferred_capture = deferred_capture,
        max_allowed_amount = max_allowed_amount,
        min_allowed_amount = min_allowed_amount,
        payment_type_id = payment_type_id,
        processing_modes = processing_modes,
        secure_thumbnail = secure_thumbnail,
        thumbnail = thumbnail
    )
}