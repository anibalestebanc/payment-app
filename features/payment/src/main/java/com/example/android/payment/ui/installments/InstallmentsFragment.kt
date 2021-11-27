package com.example.android.payment.ui.installments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentInstallmentsBinding
import com.example.android.payment.presentation.installments.InstallmentsViewModel
import com.example.android.payment.presentation.installments.model.InstallmentUiState
import com.example.android.payment.presentation.installments.model.UiPayerCost
import com.example.android.payment.ui.di.DaggerPaymentComponent
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.paymentapp.android.di.DaggerDependencies
import com.example.utils.android.android.BaseBindingFragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class InstallmentsFragment : BaseBindingFragment<FragmentInstallmentsBinding>(
    R.layout.fragment_installments
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: InstallmentsFragmentArgs by navArgs()
    private val viewModel: InstallmentsViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: InstallmentAdapter

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
        setupObservers()
        setupClickListener()
        viewModel.getInstallments(
            amount = args.bank.amount,
            paymentMethodId = args.bank.paymentMethodId,
            bankId = args.bank.bankId
        )
    }

    private fun setupClickListener() {
        binding.payButton.setOnClickListener {
            val uiPayerCost = adapter.getInstallmentSelected()
            PaymentNavigator.goToPaymentResultScreen(
                view = binding.root,
                uiBankSelected = args.bank,
                installmentMessage = uiPayerCost.recommended_message
            )
        }
        binding.retryView.retryButton.setOnClickListener {
            viewModel.getInstallments(
                amount = args.bank.amount,
                paymentMethodId = args.bank.paymentMethodId,
                bankId = args.bank.bankId
            )
        }
    }

    private fun setupObservers() {
        viewModel.installmentState.observe(viewLifecycleOwner, Observer(::renderUiState))
    }

    private fun renderUiState(uiState: InstallmentUiState) {
        when (uiState) {
            InstallmentUiState.DefaultState -> Unit
            InstallmentUiState.LoadingState -> displayLoadingView()
            is InstallmentUiState.ErrorState -> displayErrorView()
            is InstallmentUiState.SuccessState -> displayInstallments(
                uiState.installmentList
            )
        }
    }

    private fun displayInstallments(installmentList: List<UiPayerCost>) {
        binding.loadingView.root.visibility = View.GONE
        adapter = InstallmentAdapter(installmentList, ::onClickedInstallment)
        binding.recyclerView.adapter = adapter
    }

    private fun onClickedInstallment(isSelected: Boolean) {
        binding.payButton.isEnabled = isSelected
    }

    private fun displayLoadingView() {
        binding.retryView.root.visibility = View.GONE
        binding.loadingView.root.visibility = View.VISIBLE
    }

    private fun displayErrorView() {
        binding.loadingView.root.visibility = View.GONE
        binding.retryView.root.visibility = View.VISIBLE
    }
}