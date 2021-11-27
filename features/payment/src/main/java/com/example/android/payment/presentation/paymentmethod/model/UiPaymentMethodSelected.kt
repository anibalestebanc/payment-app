package com.example.android.payment.presentation.paymentmethod.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiPaymentMethodSelected(
    val amount: Int,
    val id: String,
    val name: String,
) : Parcelable
