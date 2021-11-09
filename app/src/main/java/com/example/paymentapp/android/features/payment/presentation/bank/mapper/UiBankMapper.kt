package com.example.paymentapp.android.features.payment.presentation.bank.mapper

import com.example.paymentapp.android.features.payment.domain.model.DomainBank
import com.example.paymentapp.android.features.payment.presentation.bank.model.UiBank
import javax.inject.Inject

class UiBankMapper @Inject constructor() {

    fun DomainBank.asUiBank() = UiBank(
        id = id,
        name = name,
        secure_thumbnail = secure_thumbnail
    )
}