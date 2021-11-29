package com.example.android.payment.data.mapper

import com.example.android.payment.factory.BankFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class BankMapperTest {
    private val mapper = BankMapper()

    @Test
    fun `given bank, when call to asDomainBank, then return domain bank`() {
        val remoteBank = BankFactory.makeRemoteBank()
        val domainBank = with(mapper) { remoteBank.asDomainBank() }
        assertEquals("id", remoteBank.id, domainBank.id)
        assertEquals("status", remoteBank.status, domainBank.status)
        assertEquals(
            "processing_mode",
            remoteBank.processing_mode,
            domainBank.processing_mode
        )
        assertEquals(
            "secure_thumbnail",
            remoteBank.secure_thumbnail,
            domainBank.secure_thumbnail
        )
        assertEquals("thumbnail", remoteBank.thumbnail, domainBank.thumbnail)
    }
}