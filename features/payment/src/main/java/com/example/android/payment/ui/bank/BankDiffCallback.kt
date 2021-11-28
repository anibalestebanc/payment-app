package com.example.android.payment.ui.bank

import androidx.recyclerview.widget.DiffUtil
import com.example.android.payment.presentation.bank.model.UiBank

object BankDiffCallback {
    val BANK_DIFF_UTIL = object : DiffUtil.ItemCallback<UiBank>() {
        override fun areItemsTheSame(oldItem: UiBank, newItem: UiBank): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiBank, newItem: UiBank): Boolean {
            return oldItem == newItem
        }
    }
}