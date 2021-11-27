package com.example.android.payment.ui.bank

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentBankBinding
import com.example.android.payment.presentation.bank.BankViewModel
import com.example.android.payment.presentation.bank.model.BankUiState
import com.example.android.payment.presentation.bank.model.UiBank
import com.example.android.payment.ui.di.DaggerPaymentComponent
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.paymentapp.android.di.DaggerDependencies
import com.example.utils.android.android.BaseBindingFragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class BankFragment : BaseBindingFragment<FragmentBankBinding>(R.layout.fragment_bank) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: BankFragmentArgs by navArgs()

    private val viewModel: BankViewModel by viewModels { viewModelFactory }

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
        viewModel.getBanks(args.paymentMethod.id)
    }

    private fun setupClickListener() {
        binding.retryView.retryButton.setOnClickListener {
            viewModel.getBanks(args.paymentMethod.id)
        }
    }

    private fun setupObservers() {
        viewModel.bankState.observe(viewLifecycleOwner, Observer(::renderState))
    }

    private fun renderState(uiState: BankUiState) {
        when (uiState) {
            BankUiState.DefaultState -> Unit
            BankUiState.LoadingState -> displayLoadingView()
            is BankUiState.ErrorState -> displayErrorView()
            is BankUiState.SuccessState -> displayBanks(
                uiState.bakList
            )
        }
    }

    private fun displayBanks(bankList: List<UiBank>) {
        binding.loadingView.root.visibility = View.GONE
        val adapter = BankAdapter(bankList, ::bankClicked)
        binding.recyclerView.adapter = adapter
    }

    private fun displayLoadingView() {
        binding.retryView.root.visibility = View.GONE
        binding.loadingView.root.visibility = View.VISIBLE
    }

    private fun displayErrorView() {
        binding.loadingView.root.visibility = View.GONE
        binding.retryView.root.visibility = View.VISIBLE
    }

    private fun bankClicked(uiBank: UiBank) {
        PaymentNavigator.goToInstallmentsScreen(
            binding.root,
            amount = args.paymentMethod.amount,
            paymentMethodId = args.paymentMethod.id,
            paymentMethodName = args.paymentMethod.name,
            bankId = uiBank.id,
            bankName = uiBank.name
        )
    }
}