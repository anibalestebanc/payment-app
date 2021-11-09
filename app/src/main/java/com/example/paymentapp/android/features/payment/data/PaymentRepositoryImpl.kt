package com.example.paymentapp.android.features.payment.data

import com.example.paymentapp.android.features.payment.data.mapper.BankMapper
import com.example.paymentapp.android.features.payment.data.mapper.PayerCostMapper
import com.example.paymentapp.android.features.payment.data.mapper.PaymentMethodMapper
import com.example.paymentapp.android.features.payment.data.source.PaymentRemote
import com.example.paymentapp.android.features.payment.domain.model.DomainBank
import com.example.paymentapp.android.features.payment.domain.model.DomainPaymentMethod
import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val remote: PaymentRemote,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val bankMapper: BankMapper,
    private val payerCostMapper: PayerCostMapper
) : PaymentRepository {

    override suspend fun getPaymentMethods(): List<DomainPaymentMethod> =
        remote.getPaymentMethods().map {
            with(paymentMethodMapper) { it.asDomainPaymentMethod() }
        }

    override suspend fun getBanks(paymentMethodId: String): List<DomainBank> =
        remote.getBanks(paymentMethodId).map {
            with(bankMapper) { it.asDomainBank() }
        }

    override suspend fun getInstallments(amount: Int, paymentMethodId: String, bankId: String) =
        remote.getInstallments(amount, paymentMethodId, bankId)
            .payer_costs.map {
                with(payerCostMapper) { it.asDomainPayerCost() }
            }
}