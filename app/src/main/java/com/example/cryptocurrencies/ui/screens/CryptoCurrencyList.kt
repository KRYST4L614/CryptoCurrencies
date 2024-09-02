package com.example.cryptocurrencies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Content
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Error
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Initial
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListState.Loading
import com.example.cryptocurrencies.presentation.cryptocurrencylist.CryptoCurrencyListViewModel
import com.example.cryptocurrencies.ui.components.Chip
import com.example.cryptocurrencies.ui.components.CryptoItem
import com.example.cryptocurrencies.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    viewModel: CryptoCurrencyListViewModel = viewModel(),
    onItemClick: (id: String, name: String, image: String) -> Unit
) {
    val usdCurrency = stringResource(id = R.string.usd)
    val rubCurrency = stringResource(id = R.string.rub)

    var selectedCurrency by rememberSaveable { mutableStateOf(usdCurrency) }

    val snackBarHostState = remember { SnackbarHostState() }

    val pullRefreshState = rememberPullToRefreshState()

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.error
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.pull_to_refresh_error_messgae),
                        style = Typography.labelSmall.copy(
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        },
        topBar = {
            Column(
                modifier = Modifier
                    .shadow(4.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.crypto_currency_list_title),
                    style = Typography.titleLarge
                )

                Row(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 13.dp)
                ) {
                    Chip(
                        modifier = Modifier.padding(end = 8.dp),
                        selected = { selectedCurrency == usdCurrency },
                        label = { stringResource(id = R.string.usd) },
                    ) {
                        selectedCurrency = usdCurrency
                        viewModel.getCryptoCurrencyList(selectedCurrency)
                    }

                    Chip(
                        modifier = Modifier.padding(end = 8.dp),
                        selected = { selectedCurrency == rubCurrency },
                        label = { stringResource(id = R.string.rub) },
                    ) {
                        selectedCurrency = rubCurrency
                        viewModel.getCryptoCurrencyList(selectedCurrency)
                    }
                }
            }
        }) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            val state = viewModel.state.collectAsStateWithLifecycle().value
            Column {
                when (state) {
                    is Loading -> {
                        LoadingScreen(Modifier.fillMaxSize())
                    }

                    is Content -> {
                        ContentScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .nestedScroll(pullRefreshState.nestedScrollConnection),
                            state = state,
                            pullRefreshState = pullRefreshState,
                            onItemClick = onItemClick,
                            selectedCurrency = selectedCurrency,
                            snackBarHostState = snackBarHostState,
                            viewModel = viewModel
                        )
                    }

                    is Error -> {
                        ErrorScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 65.dp)
                        ) {
                            viewModel.getCryptoCurrencyList(selectedCurrency)
                        }
                    }

                    is Initial -> {
                        viewModel.getCryptoCurrencyList(selectedCurrency)
                    }
                }
            }

            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullRefreshState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentScreen(
    modifier: Modifier,
    state: Content,
    pullRefreshState: PullToRefreshState,
    onItemClick: (id: String, name: String, image: String) -> Unit,
    selectedCurrency: String,
    snackBarHostState: SnackbarHostState,
    viewModel: CryptoCurrencyListViewModel
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.cryptoCurrencyList) { item: CryptoCurrency ->
            CryptoItem(cryptoCurrency = item, currency = selectedCurrency) {
                onItemClick(
                    item.id,
                    item.name,
                    item.image
                )
            }
        }
    }

    var errorSnackWasDisplayed by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(pullRefreshState.isRefreshing) {
        if (pullRefreshState.isRefreshing) {
            viewModel.getCryptoCurrencyList(
                selectedCurrency,
                true
            ).join()
            pullRefreshState.endRefresh()
            errorSnackWasDisplayed = false
        }
        if (state.loadingFailed && !errorSnackWasDisplayed) {
            snackBarHostState.showSnackbar("")
            errorSnackWasDisplayed = true
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CryptoListScreenPreview() {
    CryptoListScreen { _, _, _ -> }
}