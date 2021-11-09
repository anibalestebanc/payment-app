package com.example.paymentapp.android.features.payment.ui.installments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.FragmentInstallmentsBinding
import com.example.paymentapp.android.features.payment.presentation.installments.InstallmentsViewModel
import com.example.paymentapp.android.features.payment.presentation.installments.model.InstallmentUiState
import com.example.paymentapp.android.features.payment.presentation.installments.model.UiPayerCost
import com.example.paymentapp.android.features.payment.ui.navigation.PaymentNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstallmentsFragment : Fragment(R.layout.fragment_installments) {
    private var _binding: FragmentInstallmentsBinding? = null
    private val binding: FragmentInstallmentsBinding get() = _binding!!

    private val args: InstallmentsFragmentArgs by navArgs()
    private val viewModel: InstallmentsViewModel by viewModels()

    private lateinit var adapter: InstallmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInstallmentsBinding.bind(view)
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
    }

    private fun setupObservers() {
        viewModel.installmentState.observe(viewLifecycleOwner, Observer(::renderUiState))
    }

    private fun renderUiState(uiState: InstallmentUiState) {
        when (uiState) {
            InstallmentUiState.DefaultState -> Unit
            InstallmentUiState.LoadingState -> displayLoadingView()
            is InstallmentUiState.ErrorState -> Unit
            is InstallmentUiState.SuccessState -> displayInstallments(uiState.installmentList)
        }
    }

    private fun displayInstallments(installmentList: List<UiPayerCost>) {
        binding.loadingView.visibility = View.GONE
        adapter = InstallmentAdapter(installmentList, ::onClickedInstallment)
        binding.recyclerView.adapter = adapter
    }

    private fun onClickedInstallment(isSelected: Boolean) {
        binding.payButton.isEnabled = isSelected
    }

    private fun displayLoadingView() {
        binding.loadingView.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}