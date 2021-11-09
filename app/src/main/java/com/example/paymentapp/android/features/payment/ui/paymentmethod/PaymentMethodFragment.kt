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
import com.example.paymentapp.android.features.payment.presentation.paymentmethod.model.UiPaymentMethod
import com.example.paymentapp.android.features.payment.ui.navigation.PaymentNavigator
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
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.paymentMethodState.observe(viewLifecycleOwner, Observer(::renderState))
    }

    private fun renderState(uiState: PaymentMethodUiState) {
        when (uiState) {
            PaymentMethodUiState.DefaultState -> Unit
            PaymentMethodUiState.LoadingState -> displayLoadingView()
            is PaymentMethodUiState.ErrorState -> Unit
            is PaymentMethodUiState.SuccessState -> displayPaymentMethods(uiState.paymentMethodList)
        }
    }

    private fun displayPaymentMethods(paymentMethodList: List<UiPaymentMethod>) {
        binding.loadingView.visibility = View.GONE
        val adapter = PaymentMethodAdapter(paymentMethodList, ::paymentMethodClicked)
        binding.recyclerView.adapter = adapter
    }

    private fun paymentMethodClicked(uiPaymentMethod: UiPaymentMethod) {
        PaymentNavigator.goToBankScreen(binding.root, args.amount, uiPaymentMethod)
    }

    private fun displayLoadingView() {
        binding.loadingView.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}