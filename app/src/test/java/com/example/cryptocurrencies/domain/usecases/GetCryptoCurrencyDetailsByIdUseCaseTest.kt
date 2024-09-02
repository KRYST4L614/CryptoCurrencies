package com.example.cryptocurrencies.domain.usecases

import com.example.cryptocurrencies.domain.entities.CryptoCurrencyDetails
import com.example.cryptocurrencies.domain.repositories.CryptoCurrencyDetailsRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCryptoCurrencyDetailsByIdUseCaseTest {
    private val mockId = "MOCK"
    private val cryptoCurrencyDetailsRepository: CryptoCurrencyDetailsRepository = mock()
    private val getCryptoCurrencyDetailsByIdUseCase =
        GetCryptoCurrencyDetailsByIdUseCase(cryptoCurrencyDetailsRepository)

    @BeforeEach
    fun setupMockito() {
        Mockito.reset(cryptoCurrencyDetailsRepository)
    }

    @Test
    fun `WHEN invoke GetCryptoCurrencyDetailsByIdUseCase EXPECT success result`() = runTest {
        val mockResponse = CryptoCurrencyDetails(
            categories = listOf("Mock"),
            description = mapOf("EN" to "Description")
        )

        whenever(cryptoCurrencyDetailsRepository.getCryptoCurrencyDetailsById(mockId))
            .thenReturn(mockResponse)

        val result = getCryptoCurrencyDetailsByIdUseCase(mockId)

        assertEquals(Result.success(mockResponse), result)
    }

    @Test
    fun `WHEN CryptoCurrencyDetailsRepository throws exception EXPECT error result`() = runTest {
        whenever(cryptoCurrencyDetailsRepository.getCryptoCurrencyDetailsById(mockId))
            .thenThrow(RuntimeException())

        val result = getCryptoCurrencyDetailsByIdUseCase(mockId)

        assertTrue(result.isFailure)
    }
}