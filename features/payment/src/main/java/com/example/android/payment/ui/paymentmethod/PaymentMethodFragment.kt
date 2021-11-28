package com.example.android.payment.ui.paymentmethod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentMethodFragment : BaseBindingFragment<FragmentPaymentMethodBinding>(
    R.layout.fragment_payment_method
) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: PaymentMethodFragmentArgs by navArgs()

    private val viewModel: PaymentMethodViewModel by viewModels { viewModelFactory }

    private val paymentMethodAdapter = PaymentMethodAdapter(::paymentMethodClicked)

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
        setupRecyclerView()
        setupClickListener()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = paymentMethodAdapter
        }
    }

    private fun setupClickListener() {
        binding.retryView.retryButton.setOnClickListener {
            viewModel.getPaymentMethods()
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.paymentMethodState.collect(::renderState)
            }
        }
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
        paymentMethodAdapter.submitList(paymentMethodList)
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