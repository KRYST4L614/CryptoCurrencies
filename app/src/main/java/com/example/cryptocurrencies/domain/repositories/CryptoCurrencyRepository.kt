package com.example.cryptocurrencies.domain.repositories

import com.example.cryptocurrencies.domain.entities.CryptoCurrency

interface CryptoCurrencyRepository {
    suspend fun getCryptoCurrencyList(
        page: Int,
        perPage: Int,
        targetCurrency: String
    ): List<CryptoCurrency>
}