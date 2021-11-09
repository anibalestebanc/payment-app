package com.example.paymentapp.android.features.payment.data.mapper

import com.example.paymentapp.android.features.payment.factory.PayerCostFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class PayerCostMapperTest {
    private val mapper = PayerCostMapper()

    @Test
    fun `given payerCost, when call to asDomainPayerCost, then return domain payerCost`() {
        val remotePayerCost = PayerCostFactory.makeRemotePayerCost()
        val domainPayerCost = with(mapper) { remotePayerCost.asDomainPayerCost() }
        assertEquals("discount_rate", remotePayerCost.discount_rate, domainPayerCost.discount_rate)
        assertEquals(
            "installment_amount",
            remotePayerCost.installment_amount,
            domainPayerCost.installment_amount
        )
        assertEquals(
            "installment_rate",
            remotePayerCost.installment_rate,
            domainPayerCost.installment_rate
        )
        assertEquals(
            "installment_rate_collector",
            remotePayerCost.installment_rate_collector,
            domainPayerCost.installment_rate_collector
        )
        assertEquals("installments", remotePayerCost.installments, domainPayerCost.installments)
        assertEquals("labels", remotePayerCost.labels, domainPayerCost.labels)
        assertEquals(
            "max_allowed_amount",
            remotePayerCost.max_allowed_amount,
            domainPayerCost.max_allowed_amount
        )
        assertEquals(
            "min_allowed_amount",
            remotePayerCost.min_allowed_amount,
            domainPayerCost.min_allowed_amount
        )
        assertEquals(
            "payment_method_option_id",
            remotePayerCost.payment_method_option_id,
            domainPayerCost.payment_method_option_id
        )
        assertEquals(
            "recommended_message",
            remotePayerCost.recommended_message,
            domainPayerCost.recommended_message
        )
        assertEquals("total_amount", remotePayerCost.total_amount, domainPayerCost.total_amount)
    }
}