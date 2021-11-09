package com.example.paymentapp.android.features.payment.factory

import com.example.paymentapp.android.features.payment.data.remote.model.RemotePayerCost
import com.example.paymentapp.android.features.payment.domain.model.DomainPayerCost
import com.example.utils.android.testing.RandomFactory

object PayerCostFactory {

    fun makeRemotePayerCost() = RemotePayerCost(
        discount_rate = RandomFactory.generateInt(),
        installment_amount = RandomFactory.generateFloat(),
        installment_rate = RandomFactory.generateFloat(),
        installment_rate_collector = makeStringList(5),
        installments = RandomFactory.generateInt(),
        labels = makeStringList(5),
        max_allowed_amount = RandomFactory.generateInt(),
        min_allowed_amount = RandomFactory.generateInt(),
        payment_method_option_id = RandomFactory.generateString(),
        recommended_message = RandomFactory.generateString(),
        total_amount = RandomFactory.generateFloat()
    )

    fun makeRemotePayerCostList(count: Int) = (0..count).map {
        makeRemotePayerCost()
    }

    private fun makeStringList(count: Int): List<String> = (0..count).map {
        RandomFactory.generateString()
    }

    fun makeDomainPayerCost() = DomainPayerCost(
        discount_rate = RandomFactory.generateInt(),
        installment_amount = RandomFactory.generateFloat(),
        installment_rate = RandomFactory.generateFloat(),
        installment_rate_collector = makeStringList(5),
        installments = RandomFactory.generateInt(),
        labels = makeStringList(5),
        max_allowed_amount = RandomFactory.generateInt(),
        min_allowed_amount = RandomFactory.generateInt(),
        payment_method_option_id = RandomFactory.generateString(),
        recommended_message = RandomFactory.generateString(),
        total_amount = RandomFactory.generateFloat()
    )

    fun makeDomainPayerCostList(count: Int) = (0..count).map {
        makeDomainPayerCost()
    }
}