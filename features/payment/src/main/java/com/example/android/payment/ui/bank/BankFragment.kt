package com.example.android.payment.ui.bank

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentBankBinding
import com.example.android.payment.presentation.bank.BankViewModel
import com.example.android.payment.presentation.bank.model.BankUiState
import com.example.android.payment.presentation.bank.model.UiBank
import com.example.android.payment.ui.di.DaggerPaymentComponent
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.android.paymentapp.di.DaggerDependencies
import com.example.utils.android.android.BaseBindingFragment
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class BankFragment : BaseBindingFragment<FragmentBankBinding>(R.layout.fragment_bank) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: BankFragmentArgs by navArgs()

    private val viewModel: BankViewModel by viewModels { viewModelFactory }

    private val bankAdapter = BankAdapter(::bankClicked)

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
        setupRecyclerView()
        setupObservers()
        viewModel.getBanks(args.paymentMethod.id)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = bankAdapter
        }
    }

    private fun setupClickListener() {
        binding.retryView.retryButton.setOnClickListener {
            viewModel.getBanks(args.paymentMethod.id)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bankState.collect(::renderState)
            }
        }
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
        bankAdapter.submitList(bankList)
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