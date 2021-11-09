package com.example.paymentapp.android.features.payment.ui.amount

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.FragmentAmountBinding
import com.example.paymentapp.android.features.payment.ui.navigation.PaymentNavigator

class AmountFragment : Fragment(R.layout.fragment_amount) {

    private var _binding: FragmentAmountBinding? = null
    private val binding: FragmentAmountBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAmountBinding.bind(view)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}