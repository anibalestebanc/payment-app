package com.example.android.payment.presentation.installments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiInstallmentSelected(
    val amount: Int,
    val paymentMethodId: String,
    val paymentMethodName: String,
    val bankId: String,
    val bankName: String,
    val installmentMessage: String
) : Parcelable
