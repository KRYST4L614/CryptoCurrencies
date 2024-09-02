package com.example.cryptocurrencies.data.repositories

import com.example.cryptocurrencies.data.CryptoCurrencyApiService
import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoCurrencyListRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val service: CryptoCurrencyApiService
) : CryptoCurrencyListRepository {
    override suspend fun getCryptoCurrencyList(
        page: Int,
        perPage: Int,
        targetCurrency: String
    ): List<CryptoCurrency> = withContext(dispatcher) {
        service.getCryptoCurrencyList(page, perPage, targetCurrency)
    }
}