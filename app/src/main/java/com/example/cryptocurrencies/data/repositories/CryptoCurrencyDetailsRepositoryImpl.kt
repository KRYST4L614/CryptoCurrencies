package com.example.cryptocurrencies.data.repositories

import com.example.cryptocurrencies.data.CryptoCurrencyApiService
import com.example.cryptocurrencies.domain.entities.CryptoCurrencyDetails
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoCurrencyDetailsRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val service: CryptoCurrencyApiService
) : CryptoCurrencyDetailsRepository {
    override suspend fun getCryptoCurrencyDetailsById(id: String): CryptoCurrencyDetails =
        withContext(dispatcher) {
            service.getCurrencyDetailsById(id)
        }
}