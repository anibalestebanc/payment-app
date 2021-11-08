package com.example.paymentapp.android.features.payment.ui.paymentmethod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.FragmentPaymentMethodBinding

class PaymentMethodFragment : Fragment(R.layout.fragment_payment_method) {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding: FragmentPaymentMethodBinding get() = _binding!!

    private val args: PaymentMethodFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentMethodBinding.bind(view)
        binding.amount.text = args.amount.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}