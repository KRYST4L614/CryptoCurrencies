package com.example.cryptocurrencies.presentation.currencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.domain.usecases.GetCryptoCurrencyListUseCase
import com.example.cryptocurrencies.presentation.currencylist.CurrencyListState.Content
import com.example.cryptocurrencies.presentation.currencylist.CurrencyListState.Error
import com.example.cryptocurrencies.presentation.currencylist.CurrencyListState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
    private val getCryptoCurrencyListUseCase: GetCryptoCurrencyListUseCase
) : ViewModel() {
    private var lastContentState: Content? = null

    private val _state: MutableStateFlow<CurrencyListState> =
        MutableStateFlow(Loading)

    val state: StateFlow<CurrencyListState> = _state

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