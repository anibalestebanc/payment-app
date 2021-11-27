package com.example.android.payment.factory

import com.example.android.payment.data.remote.model.RemotePaymentMethod
import com.example.android.payment.domain.model.DomainPaymentMethod
import com.example.utils.android.testing.RandomFactory

object PaymentMethodFactory {

    fun makeRemotePaymentMethod() =
        com.example.android.payment.data.remote.model.RemotePaymentMethod(
            accreditation_time = RandomFactory.generateInt(),
            additional_info_needed = makeStringList(5),
            deferred_capture = RandomFactory.generateString(),
            id = RandomFactory.generateString(),
            max_allowed_amount = RandomFactory.generateInt(),
            min_allowed_amount = RandomFactory.generateInt(),
            name = RandomFactory.generateString(),
            payment_type_id = RandomFactory.generateString(),
            processing_modes = makeStringList(5),
            secure_thumbnail = RandomFactory.generateString(),
            status = RandomFactory.generateString(),
            thumbnail = RandomFactory.generateString()
        )

    fun makeRemotePaymentMethodList(count: Int): List<com.example.android.payment.data.remote.model.RemotePaymentMethod> =
        (0..count).map {
            makeRemotePaymentMethod()
        }


    fun makeDomainPaymentMethod() = com.example.android.payment.domain.model.DomainPaymentMethod(
        accreditation_time = RandomFactory.generateInt(),
        additional_info_needed = makeStringList(5),
        deferred_capture = RandomFactory.generateString(),
        id = RandomFactory.generateString(),
        max_allowed_amount = RandomFactory.generateInt(),
        min_allowed_amount = RandomFactory.generateInt(),
        name = RandomFactory.generateString(),
        payment_type_id = RandomFactory.generateString(),
        processing_modes = makeStringList(5),
        secure_thumbnail = RandomFactory.generateString(),
        status = RandomFactory.generateString(),
        thumbnail = RandomFactory.generateString()
    )

    fun makeDomainPaymentMethodList(count: Int): List<com.example.android.payment.domain.model.DomainPaymentMethod> =
        (0..count).map {
            makeDomainPaymentMethod()
        }

    private fun makeStringList(count: Int): List<String> = (0..count).map {
        RandomFactory.generateString()
    }
}