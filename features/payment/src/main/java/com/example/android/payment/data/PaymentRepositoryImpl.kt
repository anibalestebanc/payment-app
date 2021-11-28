package com.example.android.payment.data

import com.example.android.payment.data.mapper.BankMapper
import com.example.android.payment.data.mapper.PayerCostMapper
import com.example.android.payment.data.mapper.PaymentMethodMapper
import com.example.android.payment.data.source.PaymentRemote
import com.example.android.payment.domain.model.DomainBank
import com.example.android.payment.domain.model.DomainPaymentMethod
import com.example.android.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val remote: PaymentRemote,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val bankMapper: BankMapper,
    private val payerCostMapper: PayerCostMapper
) : PaymentRepository {

    override fun getPaymentMethods(): Flow<List<DomainPaymentMethod>> =
        remote.getPaymentMethods().map { remotePaymentMethod ->
            remotePaymentMethod.map {
                with(paymentMethodMapper) { it.asDomainPaymentMethod() }
            }
        }

    override fun getBanks(paymentMethodId: String): Flow<List<DomainBank>> =
        remote.getBanks(paymentMethodId).map { remoteBanks ->
            remoteBanks.map {
                with(bankMapper) { it.asDomainBank() }
            }
        }

    override fun getInstallments(amount: Int, paymentMethodId: String, bankId: String) =
        remote.getInstallments(amount, paymentMethodId, bankId)
            .map { remotePagerCost ->
                remotePagerCost.map {
                    with(payerCostMapper) { it.asDomainPayerCost() }
                }
            }

}