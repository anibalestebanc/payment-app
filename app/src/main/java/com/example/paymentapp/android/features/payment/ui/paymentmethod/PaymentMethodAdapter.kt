package com.example.paymentapp.android.features.payment.ui.paymentmethod

import android.os.Build.VERSION.SDK_INT
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.paymentapp.android.R
import com.example.paymentapp.android.databinding.ItemPaymentMethodBinding
import com.example.paymentapp.android.features.payment.presentation.paymentmethod.model.UiPaymentMethod

class PaymentMethodAdapter(
    private val list: List<UiPaymentMethod>,
    private val onClickListener: (uiPaymentMethod: UiPaymentMethod) -> Unit
) :
    RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val binding = ItemPaymentMethodBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentMethodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class PaymentMethodViewHolder(private val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(paymentMethod: UiPaymentMethod) {
            binding.paymentMethodName.text = paymentMethod.name
            val imageLoader = ImageLoader.Builder(binding.root.context)
                .componentRegistry {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder(binding.root.context))
                    } else {
                        add(GifDecoder())
                    }
                }
                .build()
            val request = ImageRequest.Builder(binding.root.context)
                .data(paymentMethod.secure_thumbnail)
                .target(binding.paymentMethodImage)
                .placeholder(R.drawable.ic_image_placeholder)
                .build()
            imageLoader.enqueue(request)
            binding.paymentMethodCard.setOnClickListener {
                onClickListener.invoke(paymentMethod)
            }
        }
    }
}