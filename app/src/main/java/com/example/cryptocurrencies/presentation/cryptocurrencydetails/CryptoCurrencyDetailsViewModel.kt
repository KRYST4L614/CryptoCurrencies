package com.example.cryptocurrencies.presentation.cryptocurrencydetails

import android.text.Html
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.domain.usecases.GetCryptoCurrencyDetailsByIdUseCase
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Content
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Error
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Initial
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CryptoCurrencyDetailsViewModel @Inject constructor(
    private val getCryptoCurrencyDetailsById: GetCryptoCurrencyDetailsByIdUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<CryptoCurrencyDetailsState> = MutableStateFlow(Initial)
    val state: StateFlow<CryptoCurrencyDetailsState> = _state

    fun getCurrencyDetailsById(
        id: String
    ) = viewModelScope.launch {
        _state.value = Loading
        getCryptoCurrencyDetailsById(id).onSuccess { response ->
            _state.value = Content(
                description = Html.fromHtml(
                    response.description.getValue("en"),
                    Html.FROM_HTML_MODE_COMPACT
                ).toString(),
                categories = response.categories.joinToString(", ")
            )
        }.onFailure {
            _state.value = Error
        }
    }
}