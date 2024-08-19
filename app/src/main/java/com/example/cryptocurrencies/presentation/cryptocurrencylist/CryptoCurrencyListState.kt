package com.example.cryptocurrencies.presentation.cryptocurrencylist

import com.example.cryptocurrencies.domain.entities.CryptoCurrency

sealed interface CryptoCurrencyListState {
    data class Content(
        val cryptoCurrencyList: List<CryptoCurrency>,
        val loadingFailed: Boolean,
    ) : CryptoCurrencyListState

    data object Loading : CryptoCurrencyListState

    data object Error : CryptoCurrencyListState
}