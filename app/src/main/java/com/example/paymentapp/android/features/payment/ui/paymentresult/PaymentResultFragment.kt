package com.example.paymentapp.android.features.payment.ui.paymentresult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.FragmentPaymentResultBinding
import com.example.paymentapp.android.features.payment.ui.navigation.PaymentNavigator

class PaymentResultFragment : Fragment(R.layout.fragment_payment_result) {

    private var _binding: FragmentPaymentResultBinding? = null
    private val binding: FragmentPaymentResultBinding get() = _binding!!

    private val args: PaymentResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentResultBinding.bind(view)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}