package com.example.android.payment.ui.di

import android.content.Context
import com.example.android.payment.ui.bank.BankFragment
import com.example.android.payment.ui.installments.InstallmentsFragment
import com.example.android.payment.ui.paymentmethod.PaymentMethodFragment
import com.example.android.paymentapp.di.DaggerDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        PaymentDataModule::class,
        PaymentPresentationModule::class
    ]
)
interface PaymentComponent {
    fun inject(fragment: PaymentMethodFragment)
    fun inject(fragment: InstallmentsFragment)
    fun inject(fragment: BankFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(dependencies: DaggerDependencies): Builder
        fun build(): PaymentComponent
    }
}