package com.example.cryptocurrencies.di

import com.example.cryptocurrencies.data.CryptoCurrencyApiService
import com.example.cryptocurrencies.data.repositories.CryptoCurrencyDetailsRepositoryImpl
import com.example.cryptocurrencies.data.repositories.CryptoCurrencyListRepositoryImpl
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyDetailsRepository
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyListRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {
    companion object {
        private const val BASE_URL = "https://api.coingecko.com/api/v3/"
    }

    @Provides
    fun provideRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideCryptoCurrencyApiService(retrofit: Retrofit) =
        retrofit.create(CryptoCurrencyApiService::class.java)

    @Provides
    fun provideCryptoCurrencyListRepository(
        apiService: CryptoCurrencyApiService
    ): CryptoCurrencyListRepository {
        return CryptoCurrencyListRepositoryImpl(Dispatchers.IO, apiService)
    }

    @Provides
    fun provideCryptoCurrencyDetailsRepository(
        apiService: CryptoCurrencyApiService
    ): CryptoCurrencyDetailsRepository {
        return CryptoCurrencyDetailsRepositoryImpl(Dispatchers.IO, apiService)
    }
}