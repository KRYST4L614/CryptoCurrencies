package com.example.cryptocurrencies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencies.presentation.ViewModelFactory
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsViewModel
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(CryptoCurrencyDetailsViewModel::class)
    fun bindCryptoCurrencyDetailsViewModel(viewModel: CryptoCurrencyDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CryptoCurrencyListViewModel::class)
    fun bindCryptoCurrencyListViewModel(viewModel: CryptoCurrencyListViewModel): ViewModel

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}