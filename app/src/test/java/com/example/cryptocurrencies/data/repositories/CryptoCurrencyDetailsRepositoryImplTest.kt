package com.example.cryptocurrencies.data.repositories

import com.example.cryptocurrencies.data.CryptoCurrencyApiService
import com.example.cryptocurrencies.domain.entities.CryptoCurrencyDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CryptoCurrencyDetailsRepositoryImplTest {
    private val apiService: CryptoCurrencyApiService = mock()
    private val mockId = "MOCK"
    private val dispatcher = Dispatchers.Unconfined
    private val cryptoCurrencyDetailsRepository =
        CryptoCurrencyDetailsRepositoryImpl(dispatcher, apiService)

    @BeforeEach
    fun setupMockito() {
        Mockito.reset(apiService)
    }

    @Test
    fun `WHEN invoke getCryptoCurrencyDetailsById EXPECT CurrencyDetails`() = runTest {
        val mockResponse = CryptoCurrencyDetails(
            categories = listOf("Mock"),
            description = mapOf("EN" to "Description")
        )

        whenever(apiService.getCurrencyDetailsById(mockId))
            .thenReturn(mockResponse)

        val result = cryptoCurrencyDetailsRepository.getCryptoCurrencyDetailsById(mockId)

        assertEquals(mockResponse, result)
    }

    @Test
    fun `WHEN apiService throws exception EXPECT that repository re-throw this exception`() =
        runTest {
            whenever(
                apiService.getCurrencyDetailsById(mockId)
            ).thenThrow(RuntimeException())

            assertThrows<RuntimeException> {
                cryptoCurrencyDetailsRepository.getCryptoCurrencyDetailsById(mockId)
            }
        }
}