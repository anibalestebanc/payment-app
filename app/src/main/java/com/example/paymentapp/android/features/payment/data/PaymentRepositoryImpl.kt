package com.example.paymentapp.android.features.payment.data

import com.example.paymentapp.android.features.payment.data.mapper.PaymentMethodMapper
import com.example.paymentapp.android.features.payment.data.source.PaymentRemote
import com.example.paymentapp.android.features.payment.domain.model.DomainPaymentMethod
import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val remote: PaymentRemote,
    private val mapper: PaymentMethodMapper
) : PaymentRepository {

    override suspend fun getPaymentMethods(): List<DomainPaymentMethod> =
        remote.getPaymentMethods().map {
            with(mapper) { it.asDomainPaymentMethod() }
        }
}