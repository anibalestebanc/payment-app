package com.example.android.payment.ui.di

import com.example.android.payment.data.PaymentRepositoryImpl
import com.example.android.payment.data.mapper.BankMapper
import com.example.android.payment.data.mapper.PayerCostMapper
import com.example.android.payment.data.mapper.PaymentMethodMapper
import com.example.android.payment.data.remote.PaymentRemoteImpl
import com.example.android.payment.data.remote.api.PaymentApi
import com.example.android.payment.data.source.PaymentRemote
import com.example.android.payment.domain.repository.PaymentRepository
import com.example.network.android.api.ApiClientFactory
import com.example.network.android.api.NetworkConstants
import com.example.android.paymentapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class PaymentDataModule {

    @Provides
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    fun providePaymentApi(): PaymentApi = ApiClientFactory<PaymentApi>().create(
        isDebug = BuildConfig.DEBUG,
        baseUrl = NetworkConstants.BASE_URL,
        tClass = PaymentApi::class.java
    )

    @Provides
    fun provideRemote(
        api: PaymentApi,
        apiKey: String
    ): PaymentRemote =
        PaymentRemoteImpl(
            api = api, apiKey = apiKey
        )

    @Provides
    fun provideRepository(
        remote: PaymentRemote,
        mapper: PaymentMethodMapper,
        bankMapper: BankMapper,
        payerCostMapper: PayerCostMapper
    ): PaymentRepository =
        PaymentRepositoryImpl(
            remote = remote,
            paymentMethodMapper = mapper,
            bankMapper = bankMapper,
            payerCostMapper = payerCostMapper
        )
}