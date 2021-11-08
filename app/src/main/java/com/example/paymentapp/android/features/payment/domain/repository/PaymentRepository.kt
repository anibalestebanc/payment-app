package com.example.paymentapp.android.features.payment.domain.repository

import com.example.paymentapp.android.features.payment.domain.model.DomainPaymentMethod

interface PaymentRepository {
    suspend fun getPaymentMethods(): List<DomainPaymentMethod>
}