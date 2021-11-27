package com.example.android.payment.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.payment.presentation.bank.BankViewModel
import com.example.android.payment.presentation.installments.InstallmentsViewModel
import com.example.android.payment.presentation.paymentmethod.PaymentMethodViewModel
import com.example.paymentapp.android.di.ViewModelFactory
import com.example.paymentapp.android.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class PaymentPresentationModule {


    @Binds
    @IntoMap
    @ViewModelKey(BankViewModel::class)
    internal abstract fun provideBankViewModel(viewModel: BankViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(InstallmentsViewModel::class)
    internal abstract fun provideInstallmentsViewModel(viewModel: InstallmentsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentMethodViewModel::class)
    internal abstract fun provideLoginViewModel(viewModel: PaymentMethodViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}