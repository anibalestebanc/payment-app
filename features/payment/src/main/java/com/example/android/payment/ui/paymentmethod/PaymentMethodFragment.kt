package com.example.android.payment.ui.paymentmethod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentPaymentMethodBinding
import com.example.android.payment.presentation.paymentmethod.PaymentMethodViewModel
import com.example.android.payment.presentation.paymentmethod.model.PaymentMethodUiState
import com.example.android.payment.presentation.paymentmethod.model.UiPaymentMethod
import com.example.android.payment.ui.di.DaggerPaymentComponent
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.android.paymentapp.di.DaggerDependencies
import com.example.utils.android.android.BaseBindingFragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class PaymentMethodFragment : BaseBindingFragment<FragmentPaymentMethodBinding>(
    R.layout.fragment_payment_method
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val args: PaymentMethodFragmentArgs by navArgs()
    private val viewModel: PaymentMethodViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerPaymentComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    DaggerDependencies::class.java
                )

            ).build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
        setupObservers()
    }

    private fun setupClickListener() {
        binding.retryView.retryButton.setOnClickListener {
            viewModel.getPaymentMethods()
        }
    }

    private fun setupObservers() {
        viewModel.paymentMethodState.observe(viewLifecycleOwner, Observer(::renderState))
    }

    private fun renderState(uiState: PaymentMethodUiState) {
        when (uiState) {
            PaymentMethodUiState.DefaultState -> Unit
            PaymentMethodUiState.LoadingState -> displayLoadingView()
            is PaymentMethodUiState.ErrorState -> displayErrorView()
            is PaymentMethodUiState.SuccessState -> displayPaymentMethods(uiState.paymentMethodList)
        }
    }

    private fun displayPaymentMethods(paymentMethodList: List<UiPaymentMethod>) {
        binding.loadingView.root.visibility = View.GONE
        val adapter = PaymentMethodAdapter(paymentMethodList, ::paymentMethodClicked)
        binding.recyclerView.adapter = adapter
    }

    private fun paymentMethodClicked(uiPaymentMethod: UiPaymentMethod) {
        PaymentNavigator.goToBankScreen(binding.root, args.amount, uiPaymentMethod)
    }

    private fun displayErrorView() {
        binding.loadingView.root.visibility = View.GONE
        binding.retryView.root.visibility = View.VISIBLE
    }

    private fun displayLoadingView() {
        binding.retryView.root.visibility = View.GONE
        binding.loadingView.root.visibility = View.VISIBLE
    }
}