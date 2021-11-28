package com.example.android.payment.ui.bank

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.android.payment.R
import com.example.android.payment.databinding.ItemBankBinding
import com.example.android.payment.presentation.bank.model.UiBank
import com.example.android.payment.ui.bank.BankDiffCallback.BANK_DIFF_UTIL

class BankAdapter(
    private val onClickListener: (uiBank: UiBank) -> Unit
) : ListAdapter<UiBank, BankAdapter.BankViewHolder>(BANK_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val binding = ItemBankBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    inner class BankViewHolder(private val binding: ItemBankBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uiBank: UiBank) {
            binding.bankName.text = uiBank.name
            val imageLoader = ImageLoader.Builder(binding.root.context)
                .componentRegistry {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder(binding.root.context))
                    } else {
                        add(GifDecoder())
                    }
                }
                .build()
            val request = ImageRequest.Builder(binding.root.context)
                .data(uiBank.secure_thumbnail)
                .target(binding.bankImage)
                .placeholder(R.drawable.ic_image_placeholder)
                .build()
            imageLoader.enqueue(request)
            binding.bankCard.setOnClickListener {
                onClickListener.invoke(uiBank)
            }
        }
    }
}