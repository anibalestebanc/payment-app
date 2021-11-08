package com.example.paymentapp.android.features.payment.ui.amount

import android.view.View
import androidx.navigation.findNavController

object AmountNavigator {

    fun goToPaymentMethod(view: View, amount: Int) {
        val action = AmountFragmentDirections.actionGoToPaymentMethod(amount)
        view.findNavController().navigate(action)
    }
}