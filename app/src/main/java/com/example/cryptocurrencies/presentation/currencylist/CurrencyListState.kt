package com.example.cryptocurrencies.presentation.currencylist

import com.example.cryptocurrencies.domain.entities.CryptoCurrency

sealed interface CurrencyListState {
    data class Content(
        val cryptoCurrencyList: List<CryptoCurrency>,
        val loadingFailed: Boolean,
    ) : CurrencyListState

    data object Loading : CurrencyListState

    data object Error : CurrencyListState
}