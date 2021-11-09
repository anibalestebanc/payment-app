package com.example.paymentapp.android.features.payment.data.mapper

import com.example.paymentapp.android.features.payment.data.remote.model.RemoteBank
import com.example.paymentapp.android.features.payment.domain.model.DomainBank
import javax.inject.Inject

class BankMapper @Inject constructor() {

    fun RemoteBank.asDomainBank() = DomainBank(
        id = id,
        name = name,
        status = status,
        processing_mode = processing_mode,
        secure_thumbnail = secure_thumbnail,
        thumbnail = thumbnail
    )
}