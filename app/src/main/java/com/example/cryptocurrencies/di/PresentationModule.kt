package com.example.cryptocurrencies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencies.presentation.ViewModelFactory
import com.example.cryptocurrencies.presentation.currencydetails.CurrencyDetailsViewModel
import com.example.cryptocurrencies.presentation.currencylist.CurrencyListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyDetailsViewModel::class)
    fun bindCurrencyDetailsViewModel(viewModel: CurrencyDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    fun bindCurrencyListViewModel(viewModel: CurrencyListViewModel): ViewModel

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}