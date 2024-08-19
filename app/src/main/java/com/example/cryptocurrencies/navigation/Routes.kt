package com.example.cryptocurrencies.navigation

import kotlinx.serialization.Serializable

@Serializable
object CryptoCurrencyListScreen

@Serializable
data class CryptoCurrencyDetailsScreen(
    val id: String,
    val name: String,
    val image: String
)