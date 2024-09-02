package com.example.cryptocurrencies.domain.usecases

import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyListRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCryptoCurrencyListUseCaseTest {
    val mockCurrency = "USD"
    private val cryptoCurrencyListRepository: CryptoCurrencyListRepository = mock()
    private val getCryptoCurrencyListUseCase =
        GetCryptoCurrencyListUseCase(cryptoCurrencyListRepository)

    @BeforeEach
    fun setupMockito() {
        Mockito.reset(cryptoCurrencyListRepository)
    }

    @Test
    fun `WHEN invoke GetCryptoCurrencyListUseCase EXPECT success result`() = runTest {
        val mockResponse = listOf(
            CryptoCurrency(
                id = "1",
                name = "Bitcoin",
                symbol = "BTC",
                image = "http://mock.com/icon.png",
                currentPrice = 1000.0,
                priceChangePercentage24h = 1.5
            ),
            CryptoCurrency(
                id = "2",
                name = "Ton",
                symbol = "TON",
                image = "http://mock.com/icon.png",
                currentPrice = 1984.0,
                priceChangePercentage24h = 12.78
            )
        )

        whenever(
            cryptoCurrencyListRepository.getCryptoCurrencyList(
                page = 1,
                perPage = 2,
                mockCurrency
            )
        ).thenReturn(mockResponse)

        val result = getCryptoCurrencyListUseCase(page = 1, perPage = 2, mockCurrency)

        assertEquals(Result.success(mockResponse), result)
    }

    @Test
    fun `WHEN CryptoCurrencyListRepository throws exception EXPECT error result`() = runTest {
        whenever(
            cryptoCurrencyListRepository.getCryptoCurrencyList(
                page = 1,
                perPage = 1,
                mockCurrency
            )
        ).thenThrow(RuntimeException())

        val result = getCryptoCurrencyListUseCase(page = 1, perPage = 1, mockCurrency)

        assertTrue(result.isFailure)
    }
}