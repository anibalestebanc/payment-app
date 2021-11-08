package com.example.paymentapp.android.di

import com.example.network.android.api.ApiClientFactory
import com.example.network.android.api.NetworkConstants
import com.example.paymentapp.android.BuildConfig
import com.example.paymentapp.android.features.payment.data.PaymentRepositoryImpl
import com.example.paymentapp.android.features.payment.data.mapper.PaymentMethodMapper
import com.example.paymentapp.android.features.payment.data.remote.PaymentRemoteImpl
import com.example.paymentapp.android.features.payment.data.remote.api.PaymentApi
import com.example.paymentapp.android.features.payment.data.source.PaymentRemote
import com.example.paymentapp.android.features.payment.domain.repository.PaymentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PaymentDataModule {

    @Provides
    fun provideApiKey() = BuildConfig.API_KEY

    @Provides
    fun providePaymentApi(): PaymentApi = ApiClientFactory<PaymentApi>().create(
        isDebug = BuildConfig.DEBUG,
        baseUrl = NetworkConstants.BASE_URL,
        tClass = PaymentApi::class.java
    )

    @Provides
    fun provideRemote(api: PaymentApi, apiKey: String): PaymentRemote = PaymentRemoteImpl(
        api = api, apiKey = apiKey
    )

    @Provides
    fun provideRepository(
        remote: PaymentRemote,
        mapper: PaymentMethodMapper
    ): PaymentRepository = PaymentRepositoryImpl(
        remote = remote,
        mapper = mapper
    )
}