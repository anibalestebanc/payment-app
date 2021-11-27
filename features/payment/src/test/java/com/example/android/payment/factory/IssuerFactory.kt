package com.example.android.payment.factory

import com.example.android.payment.data.remote.model.RemoteIssuer
import com.example.utils.android.testing.RandomFactory

object IssuerFactory {
    fun makeRemoteIssuer() = com.example.android.payment.data.remote.model.RemoteIssuer(
        id = RandomFactory.generateString(),
        name = RandomFactory.generateString(),
        secure_thumbnail = RandomFactory.generateString(),
        thumbnail = RandomFactory.generateString()
    )
}