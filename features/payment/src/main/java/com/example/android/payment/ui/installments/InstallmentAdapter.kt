package com.example.android.payment.ui.installments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.payment.databinding.ItemInstallmentBinding
import com.example.android.payment.presentation.installments.model.UiPayerCost


class InstallmentAdapter(
    private val list: List<UiPayerCost>,
    private val onClickInstalment: (isSelected: Boolean) -> Unit
) : RecyclerView.Adapter<InstallmentAdapter.InstallmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentViewHolder {
        val binding = ItemInstallmentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return InstallmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstallmentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    private fun unselectAllItems(uiPayerCost: com.example.android.payment.presentation.installments.model.UiPayerCost) {
        for ((index, payerCost) in list.withIndex()) {
            if (payerCost.installments != uiPayerCost.installments && payerCost.isSelected) {
                payerCost.isSelected = false
                notifyItemChanged(index)
            }
        }
    }

    fun getInstallmentSelected(): com.example.android.payment.presentation.installments.model.UiPayerCost {
        for (payerCost in list.iterator()) {
            if (payerCost.isSelected) {
                return payerCost
            }
        }
        throw Exception("Error to get installment")
    }


    inner class InstallmentViewHolder(private val binding: ItemInstallmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uiPayerCost: com.example.android.payment.presentation.installments.model.UiPayerCost) {
            binding.installmentDescription.text = uiPayerCost.recommended_message
            binding.instalmentRadioButton.isChecked = uiPayerCost.isSelected
            binding.instalmentRadioButton.setOnCheckedChangeListener { buttonView, isChecked ->
                uiPayerCost.isSelected = isChecked
                if (isChecked) {
                    onClickInstalment(true)
                    unselectAllItems(uiPayerCost)
                }
            }
        }
    }
}