package com.example.paymentapp.android.features.payment.ui.paymentmethod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.FragmentPaymentMethodBinding
import com.example.paymentapp.android.features.payment.presentation.paymentmethod.PaymentMethodViewModel
import com.example.paymentapp.android.features.payment.presentation.paymentmethod.model.PaymentMethodUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodFragment : Fragment(R.layout.fragment_payment_method) {
    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding: FragmentPaymentMethodBinding get() = _binding!!

    private val args: PaymentMethodFragmentArgs by navArgs()

    private val viewModel: PaymentMethodViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentMethodBinding.bind(view)
        binding.amount.text = args.amount.toString()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.paymentMethodState.observe(viewLifecycleOwner, Observer(::renderState))
    }

    private fun renderState(uiState: PaymentMethodUiState) {
        when (uiState) {
            PaymentMethodUiState.DefaultState -> Unit
            is PaymentMethodUiState.ErrorState -> Unit
            PaymentMethodUiState.LoadingState -> Unit
            PaymentMethodUiState.SuccessState -> Unit
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}