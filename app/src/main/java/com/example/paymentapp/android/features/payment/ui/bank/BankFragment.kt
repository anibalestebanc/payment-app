package com.example.paymentapp.android.features.payment.ui.bank

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.FragmentBankBinding
import com.example.paymentapp.android.features.payment.presentation.bank.BankViewModel
import com.example.paymentapp.android.features.payment.presentation.bank.model.BankUiState
import com.example.paymentapp.android.features.payment.presentation.bank.model.UiBank
import com.example.paymentapp.android.features.payment.ui.navigation.PaymentNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankFragment : Fragment(R.layout.fragment_bank) {
    private var _binding: FragmentBankBinding? = null
    private val binding: FragmentBankBinding get() = _binding!!

    private val args: BankFragmentArgs by navArgs()

    private val viewModel: BankViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBankBinding.bind(view)
        setupObservers()
        viewModel.getBanks(args.paymentMethod.id)
    }

    private fun setupObservers() {
        viewModel.bankState.observe(viewLifecycleOwner, Observer(::renderState))
    }

    private fun renderState(uiState: BankUiState) {
        when (uiState) {
            BankUiState.DefaultState -> Unit
            BankUiState.LoadingState -> displayLoadingView()
            is BankUiState.ErrorState -> Unit
            is BankUiState.SuccessState -> displayBanks(uiState.bakList)
        }
    }

    private fun displayBanks(bankList: List<UiBank>) {
        binding.loadingView.visibility = View.GONE
        val adapter = BankAdapter(bankList, ::bankClicked)
        binding.recyclerView.adapter = adapter
    }

    private fun displayLoadingView() {
        binding.loadingView.visibility = View.VISIBLE
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}