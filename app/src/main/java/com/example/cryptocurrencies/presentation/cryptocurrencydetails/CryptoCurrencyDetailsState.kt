package com.example.cryptocurrencies.presentation.cryptocurrencydetails

sealed interface CryptoCurrencyDetailsState {
    data class Content(
        val description: String,
        val categories: String
    ) : CryptoCurrencyDetailsState

    data object Loading : CryptoCurrencyDetailsState

    data object Error : CryptoCurrencyDetailsState
}