package com.example.android.payment.factory

import com.example.android.payment.data.remote.model.RemoteInstallment
import com.example.utils.android.testing.RandomFactory

object InstallmentFactory {
    fun makeRemoteInstallment() = RemoteInstallment(
        issuer = IssuerFactory.makeRemoteIssuer(),
        payer_costs = PayerCostFactory.makeRemotePayerCostList(5),
        payment_type_id = RandomFactory.generateString(),
        payment_method_id = RandomFactory.generateString(),
        processing_mode = RandomFactory.generateString()
    )

    fun makeRemoteInstallmentList(count :Int) = (0..count).map {
        makeRemoteInstallment()
    }
}