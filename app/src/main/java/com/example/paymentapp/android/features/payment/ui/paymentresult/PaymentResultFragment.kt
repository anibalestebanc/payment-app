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
        binding.amountText.text = "Monto : ${args.installment.amount}"
        binding.paymentMethodText.text = "Metodo de pago : ${args.installment.paymentMethodName}"
        binding.bankText.text = "Banco : ${args.installment.bankName}"
        binding.installmentText.text = "Cuotas : ${args.installment.installmentMessage}"
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