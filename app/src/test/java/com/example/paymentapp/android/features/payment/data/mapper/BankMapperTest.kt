package com.example.paymentapp.android.features.payment.data.mapper

import com.example.paymentapp.android.features.payment.factory.BankFactory
import org.junit.Assert
import org.junit.Test

class BankMapperTest {
    private val mapper = BankMapper()

    @Test
    fun `given bank, when call to asDomainBank, then return domain bank`() {
        val remoteBank = BankFactory.makeRemoteBank()
        val domainBank = with(mapper) { remoteBank.asDomainBank() }
        Assert.assertEquals("id", remoteBank.id, domainBank.id)
        Assert.assertEquals("status", remoteBank.status, domainBank.status)
        Assert.assertEquals(
            "processing_mode",
            remoteBank.processing_mode,
            domainBank.processing_mode
        )
        Assert.assertEquals(
            "secure_thumbnail",
            remoteBank.secure_thumbnail,
            domainBank.secure_thumbnail
        )
        Assert.assertEquals("thumbnail", remoteBank.thumbnail, domainBank.thumbnail)
    }
}