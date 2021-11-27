package com.example.android.payment.ui.navigation

import android.view.View
import androidx.navigation.findNavController
import com.example.android.payment.R
import com.example.android.payment.presentation.bank.model.UiBankSelected
import com.example.android.payment.presentation.installments.model.UiInstallmentSelected
import com.example.android.payment.presentation.paymentmethod.model.UiPaymentMethod
import com.example.android.payment.presentation.paymentmethod.model.UiPaymentMethodSelected
import com.example.android.payment.ui.amount.AmountFragmentDirections
import com.example.android.payment.ui.bank.BankFragmentDirections
import com.example.android.payment.ui.installments.InstallmentsFragmentDirections
import com.example.android.payment.ui.paymentmethod.PaymentMethodFragmentDirections

object PaymentNavigator {

    fun goToPaymentMethodScreen(view: View, amount: Int) {
        val action = AmountFragmentDirections.actionGoToPaymentMethod(amount)
        view.findNavController().navigate(action)
    }

    fun goToBankScreen(
        view: View,
        amount: Int,
        uiPaymentMethod: UiPaymentMethod
    ) {
        val paymentMethod = UiPaymentMethodSelected(
            amount = amount,
            id = uiPaymentMethod.id,
            name = uiPaymentMethod.name
        )
        val action = PaymentMethodFragmentDirections.actionGoToBankFragment(paymentMethod)
        view.findNavController().navigate(action)
    }

    fun goToInstallmentsScreen(
        view: View, amount: Int, paymentMethodId: String, paymentMethodName: String,
        bankId: String, bankName: String
    ) {
        val bank = UiBankSelected(
            amount = amount,
            paymentMethodId = paymentMethodId,
            paymentMethodName = paymentMethodName,
            bankId = bankId,
            bankName = bankName
        )
        val action = BankFragmentDirections.actionGoToInstallmentsFragment(bank)
        view.findNavController().navigate(action)
    }

    fun goToPaymentResultScreen(
        view: View,
        uiBankSelected: UiBankSelected,
        installmentMessage: String
    ) {
        val installmentSelected = UiInstallmentSelected(
            amount = uiBankSelected.amount,
            paymentMethodId = uiBankSelected.paymentMethodId,
            paymentMethodName = uiBankSelected.paymentMethodName,
            bankId = uiBankSelected.bankId,
            bankName = uiBankSelected.bankName,
            installmentMessage = installmentMessage
        )
        val action =
            InstallmentsFragmentDirections.actionGoToPaymentResultFragment(installmentSelected)
        view.findNavController().navigate(action)
    }


    fun goToAmountScreen(view: View) {
        view.findNavController().navigate(R.id.action_go_to_amount_fragment)
    }
}