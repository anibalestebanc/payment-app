package com.example.android.payment.data.mapper

import com.example.android.payment.factory.PaymentMethodFactory
import org.junit.Assert.assertEquals
import org.junit.Test


class PaymentMethodMapperTest {
    private val mapper = com.example.android.payment.data.mapper.PaymentMethodMapper()

    @Test
    fun `given payment method, when call to asDomainPaymentMethod, then return domain payment method`() {
        val remotePaymentMethod = com.example.android.payment.factory.PaymentMethodFactory.makeRemotePaymentMethod()
        val domainPaymentMethod = with(mapper) { remotePaymentMethod.asDomainPaymentMethod() }
        assertEquals("id", remotePaymentMethod.id, domainPaymentMethod.id)
        assertEquals("name", remotePaymentMethod.name, domainPaymentMethod.name)
        assertEquals("status", remotePaymentMethod.status, domainPaymentMethod.status)
        assertEquals("accreditation_time", remotePaymentMethod.accreditation_time, domainPaymentMethod.accreditation_time)
        assertEquals("additional_info_needed", remotePaymentMethod.additional_info_needed, domainPaymentMethod.additional_info_needed)
        assertEquals("deferred_capture", remotePaymentMethod.deferred_capture, domainPaymentMethod.deferred_capture)
        assertEquals("max_allowed_amount", remotePaymentMethod.max_allowed_amount, domainPaymentMethod.max_allowed_amount)
        assertEquals("min_allowed_amount", remotePaymentMethod.min_allowed_amount, domainPaymentMethod.min_allowed_amount)
        assertEquals("payment_type_id", remotePaymentMethod.payment_type_id, domainPaymentMethod.payment_type_id)
        assertEquals("processing_modes", remotePaymentMethod.processing_modes, domainPaymentMethod.processing_modes)
        assertEquals("secure_thumbnail", remotePaymentMethod.secure_thumbnail, domainPaymentMethod.secure_thumbnail)
        assertEquals("thumbnail", remotePaymentMethod.thumbnail, domainPaymentMethod.thumbnail)
    }
}