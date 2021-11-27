package com.example.android.payment.factory

import com.example.android.payment.data.remote.model.RemoteBank
import com.example.android.payment.domain.model.DomainBank
import com.example.utils.android.testing.RandomFactory

object BankFactory {

    fun makeRemoteBank() = com.example.android.payment.data.remote.model.RemoteBank(
        id = RandomFactory.generateString(),
        name = RandomFactory.generateString(),
        status = RandomFactory.generateString(),
        processing_mode = RandomFactory.generateString(),
        secure_thumbnail = RandomFactory.generateString(),
        thumbnail = RandomFactory.generateString()
    )

    fun makeRemoteBankList(count: Int): List<com.example.android.payment.data.remote.model.RemoteBank> = (0..count).map {
        makeRemoteBank()
    }

    fun makeDomainBank() = com.example.android.payment.domain.model.DomainBank(
        id = RandomFactory.generateString(),
        name = RandomFactory.generateString(),
        status = RandomFactory.generateString(),
        processing_mode = RandomFactory.generateString(),
        secure_thumbnail = RandomFactory.generateString(),
        thumbnail = RandomFactory.generateString()
    )

    fun makeDomainBankList(count: Int): List<com.example.android.payment.domain.model.DomainBank> = (0..count).map {
        makeDomainBank()
    }

}