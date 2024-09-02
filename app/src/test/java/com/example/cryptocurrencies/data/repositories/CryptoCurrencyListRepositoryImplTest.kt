package com.example.cryptocurrencies.data.repositories

import com.example.cryptocurrencies.data.CryptoCurrencyApiService
import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CryptoCurrencyListRepositoryImplTest {
    private val apiService: CryptoCurrencyApiService = mock()
    private val dispatcher = Dispatchers.Unconfined
    private val cryptoCurrencyListRepository =
        CryptoCurrencyListRepositoryImpl(dispatcher, apiService)

    @BeforeEach
    fun setupMockito() {
        Mockito.reset(apiService)
    }

    @Test
    fun `WHEN invoke getCryptoCurrencyList EXPECT list of CryptoCurrency`() = runTest {
        val currency = "USD"
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

        whenever(apiService.getCryptoCurrencyList(page = 1, perPage = 2, vsCurrency = currency))
            .thenReturn(mockResponse)

        val result = cryptoCurrencyListRepository.getCryptoCurrencyList(
            page = 1,
            perPage = 2,
            targetCurrency = currency
        )

        assertEquals(mockResponse, result)
    }

    @Test
    fun `WHEN apiService throws exception EXPECT that repository re-throw this exception`() =
        runTest {
            val currency = "USD"

            whenever(
                apiService.getCryptoCurrencyList(
                    page = 1,
                    perPage = 1,
                    vsCurrency = currency
                )
            ).thenThrow(RuntimeException())

            assertThrows<RuntimeException> {
                cryptoCurrencyListRepository.getCryptoCurrencyList(
                    page = 1,
                    perPage = 1,
                    targetCurrency = currency
                )
            }
        }
}