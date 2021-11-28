package com.example.android.payment.ui.installments

import androidx.recyclerview.widget.DiffUtil
import com.example.android.payment.presentation.installments.model.UiPayerCost

object InstallmentsDiffCallback {
    val INSTALLMENTS_DIFF_UTIL = object : DiffUtil.ItemCallback<UiPayerCost>() {
        override fun areItemsTheSame(oldItem: UiPayerCost, newItem: UiPayerCost): Boolean {
            return oldItem.recommended_message == newItem.recommended_message
        }

        override fun areContentsTheSame(oldItem: UiPayerCost, newItem: UiPayerCost): Boolean {
            return oldItem == newItem
        }
    }
}