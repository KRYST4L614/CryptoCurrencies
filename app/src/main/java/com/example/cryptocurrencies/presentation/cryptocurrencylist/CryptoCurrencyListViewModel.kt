package com.example.cryptocurrencies.presentation.cryptocurrencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.domain.usecases.GetCryptoCurrencyListUseCase
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Content
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Error
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Initial
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CryptoCurrencyListViewModel @Inject constructor(
    private val getCryptoCurrencyListUseCase: GetCryptoCurrencyListUseCase
) : ViewModel() {
    private var lastContentState: Content? = null

    private val _state: MutableStateFlow<CryptoCurrencyListState> =
        MutableStateFlow(Initial)

    val state: StateFlow<CryptoCurrencyListState> = _state

    fun getCryptoCurrencyList(
        targetCurrency: String,
        isRefresh: Boolean = false
    ) = viewModelScope.launch {
        if (!isRefresh) {
            _state.value = Loading
        }
        getCryptoCurrencyListUseCase(targetCurrency = targetCurrency).onSuccess { response ->
            lastContentState = Content(
                cryptoCurrencyList = response,
                false,
            )
            _state.value = lastContentState!!
        }.onFailure {
            if (isRefresh) {
                _state.value = lastContentState!!.copy(
                    loadingFailed = true
                )
            } else {
                _state.value = Error
            }
        }
    }
}