package com.example.android.payment.ui.paymentmethod

import androidx.recyclerview.widget.DiffUtil
import com.example.android.payment.presentation.paymentmethod.model.UiPaymentMethod

object PaymentMethodDiffCallback {
    val PAYMENT_METHOD_DIFF_UTIL = object : DiffUtil.ItemCallback<UiPaymentMethod>() {
        override fun areItemsTheSame(oldItem: UiPaymentMethod, newItem: UiPaymentMethod): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UiPaymentMethod,
            newItem: UiPaymentMethod
        ): Boolean {
            return oldItem == newItem
        }
    }
}