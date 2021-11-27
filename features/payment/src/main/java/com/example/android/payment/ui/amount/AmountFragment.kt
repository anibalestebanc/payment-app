package com.example.android.payment.ui.amount

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.android.payment.R
import com.example.android.payment.databinding.FragmentAmountBinding
import com.example.android.payment.ui.navigation.PaymentNavigator
import com.example.utils.android.android.BaseBindingFragment

class AmountFragment : BaseBindingFragment<FragmentAmountBinding>(R.layout.fragment_amount) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        binding.amountEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    binding.nextButton.isEnabled = false
                    return
                }
                if (s.toString() == "0") {
                    binding.nextButton.isEnabled = false
                    return
                }

                binding.nextButton.isEnabled = true
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setupClickListeners() {
        binding.nextButton.setOnClickListener {
            PaymentNavigator.goToPaymentMethodScreen(
                binding.root,
                binding.amountEdit.text.toString().toInt()
            )
        }
    }
}