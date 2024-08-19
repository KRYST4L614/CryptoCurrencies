package com.example.cryptocurrencies.presentation.currencydetails

import android.text.Html
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.domain.usecases.GetCryptoCurrencyDetailsById
import com.example.cryptocurrencies.presentation.currencydetails.CurrencyDetailsState.Content
import com.example.cryptocurrencies.presentation.currencydetails.CurrencyDetailsState.Error
import com.example.cryptocurrencies.presentation.currencydetails.CurrencyDetailsState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyDetailsViewModel @Inject constructor(
    private val getCryptoCurrencyDetailsById: GetCryptoCurrencyDetailsById,
) : ViewModel() {
    private val _state: MutableStateFlow<CurrencyDetailsState> = MutableStateFlow(Loading)
    val state: StateFlow<CurrencyDetailsState> = _state

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