package com.example.cryptocurrencies.domain.usecases

import com.example.cryptocurrencies.domain.entities.CryptoCurrencyDetails
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyDetailsRepository
import javax.inject.Inject

class GetCryptoCurrencyDetailsById @Inject constructor(
    private val repository: CryptoCurrencyDetailsRepository
) {
    suspend operator fun invoke(id: String): Result<CryptoCurrencyDetails> {
        return kotlin.runCatching {
            repository.getCryptoCurrencyDetailsById(id)
        }
    }
}