package com.example.paymentapp.android.features.payment.factory

import com.example.paymentapp.android.features.payment.data.remote.model.RemoteIssuer
import com.example.utils.android.testing.RandomFactory

object IssuerFactory {
    fun makeRemoteIssuer() = RemoteIssuer(
        id = RandomFactory.generateString(),
        name = RandomFactory.generateString(),
        secure_thumbnail = RandomFactory.generateString(),
        thumbnail = RandomFactory.generateString()
    )
}