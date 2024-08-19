package com.example.cryptocurrencies.domain.repositories

import com.example.cryptocurrencies.domain.entities.CryptoCurrencyDetails

interface CryptoCurrencyDetailsRepository {
    suspend fun getCryptoCurrencyDetailsById(id: String): CryptoCurrencyDetails
}