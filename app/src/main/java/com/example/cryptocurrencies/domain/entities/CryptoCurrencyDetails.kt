package com.example.cryptocurrencies.domain.entities

data class CryptoCurrencyDetails(
    val categories: List<String>,
    val description: Map<String, String>
)