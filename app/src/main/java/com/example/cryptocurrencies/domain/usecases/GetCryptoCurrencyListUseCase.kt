package com.example.cryptocurrencies.domain.usecases

import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyListRepository
import javax.inject.Inject

class GetCryptoCurrencyListUseCase @Inject constructor(
    private val repository: CryptoCurrencyListRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        perPage: Int = 30,
        targetCurrency: String
    ): Result<List<CryptoCurrency>> {
        return kotlin.runCatching {
            repository.getCryptoCurrencyList(page, perPage, targetCurrency)
        }
    }
}