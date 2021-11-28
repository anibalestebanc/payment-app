package com.example.android.payment.ui.installments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.payment.databinding.ItemInstallmentBinding
import com.example.android.payment.presentation.installments.model.UiPayerCost
import com.example.android.payment.ui.installments.InstallmentsDiffCallback.INSTALLMENTS_DIFF_UTIL


class InstallmentAdapter(
    private val onClickInstalment: (isSelected: Boolean) -> Unit
) : ListAdapter<UiPayerCost, InstallmentAdapter.InstallmentViewHolder>(INSTALLMENTS_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentViewHolder {
        val binding = ItemInstallmentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return InstallmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstallmentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    private fun updateItems(installments: Int) {
        currentList.onEachIndexed { index, pagerCost ->
            val isSelected =  pagerCost.installments == installments
            if (pagerCost.isSelected != isSelected) {
                pagerCost.isSelected = isSelected
                notifyItemChanged(index)
            }
        }
    }

    fun getInstallmentSelected(): UiPayerCost = currentList.first { it.isSelected }

    inner class InstallmentViewHolder(private val binding: ItemInstallmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uiPayerCost: UiPayerCost) {
            binding.installmentDescription.text = uiPayerCost.recommended_message
            binding.instalmentRadioButton.isChecked = uiPayerCost.isSelected
            binding.instalmentRadioButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    onClickInstalment(true)
                    updateItems(uiPayerCost.installments)
                }
            }
        }
    }

}