package com.example.cryptocurrencies.presentation.currencydetails

sealed interface CurrencyDetailsState {
    data class Content(
        val description: String,
        val categories: String
    ) : CurrencyDetailsState

    data object Loading : CurrencyDetailsState

    data object Error : CurrencyDetailsState
}