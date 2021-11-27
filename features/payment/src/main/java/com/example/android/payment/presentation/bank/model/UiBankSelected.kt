package com.example.android.payment.presentation.bank.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiBankSelected(
    val amount: Int,
    val paymentMethodId: String,
    val paymentMethodName: String,
    val bankId: String,
    val bankName: String
) : Parcelable
