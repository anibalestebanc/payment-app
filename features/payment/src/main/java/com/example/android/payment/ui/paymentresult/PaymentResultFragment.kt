package com.example.android.payment.ui.paymentresult

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentPaymentResultBinding
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.utils.android.android.BaseBindingFragment

class PaymentResultFragment : BaseBindingFragment<FragmentPaymentResultBinding>(
    R.layout.fragment_payment_result
) {

    private val args: PaymentResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
        setupView()
    }

    private fun setupView() {
        binding.amountText.text = getString(R.string.amount, args.installment.amount.toString())
        binding.paymentMethodText.text =
            getString(R.string.payment_method, args.installment.paymentMethodName)
        binding.bankText.text = getString(R.string.bank, args.installment.bankName)
        binding.installmentText.text =
            getString(R.string.installments, args.installment.installmentMessage)
    }

    private fun setupClickListener() {
        binding.nextButton.setOnClickListener {
            PaymentNavigator.goToAmountScreen(binding.root)
        }
    }
}