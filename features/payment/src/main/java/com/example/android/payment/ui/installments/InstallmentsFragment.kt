package com.example.android.payment.ui.installments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.navArgs
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentInstallmentsBinding
import com.example.android.payment.presentation.installments.InstallmentsViewModel
import com.example.android.payment.presentation.installments.model.InstallmentUiState
import com.example.android.payment.presentation.installments.model.UiPayerCost
import com.example.android.payment.ui.di.DaggerPaymentComponent
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.android.paymentapp.di.DaggerDependencies
import com.example.utils.android.android.BaseBindingFragment
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstallmentsFragment : BaseBindingFragment<FragmentInstallmentsBinding>(
    R.layout.fragment_installments
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: InstallmentsFragmentArgs by navArgs()
    private val viewModel: InstallmentsViewModel by viewModels { viewModelFactory }

    private val installmentAdapter = InstallmentAdapter(::onClickedInstallment)

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
        setupRecyclerView()
        setupClickListener()
        viewModel.getInstallments(
            amount = args.bank.amount,
            paymentMethodId = args.bank.paymentMethodId,
            bankId = args.bank.bankId
        )
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = installmentAdapter
        }
    }

    private fun setupClickListener() {
        binding.payButton.setOnClickListener {
            val uiPayerCost = installmentAdapter.getInstallmentSelected()
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.installmentState.collect(::renderUiState)
            }
        }
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
        installmentAdapter.submitList(installmentList)
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