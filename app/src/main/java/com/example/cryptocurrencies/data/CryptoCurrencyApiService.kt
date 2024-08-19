package com.example.cryptocurrencies.data

import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import com.example.cryptocurrencies.domain.entities.CryptoCurrencyDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoCurrencyApiService {
    @GET("coins/markets")
    suspend fun getCryptoCurrencyList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("vs_currency") vsCurrency: String,
    ): List<CryptoCurrency>

    @GET("coins/{id}")
    suspend fun getCurrencyDetailsById(
        @Path("id") id: String
    ): CryptoCurrencyDetails
}