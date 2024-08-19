package com.example.cryptocurrencies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Content
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Error
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsState.Loading
import com.example.cryptocurrencies.presentation.cryptocurrencydetails.CryptoCurrencyDetailsViewModel
import com.example.cryptocurrencies.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CryptoCurrencyDetailsViewModel = viewModel(),
    id: String,
    name: String,
    image: String,
    onBackClick: () -> Unit
) {
    var wasInitialLoad by rememberSaveable {
        mutableStateOf(false)
    }
    if (!wasInitialLoad) {
        viewModel.getCurrencyDetailsById(id)
        wasInitialLoad = true
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(4.dp),
                title = { Text(text = name, style = Typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        when (val state = viewModel.state.collectAsState().value) {
            is Loading -> {
                LoadingScreen(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                )
            }

            is Content -> {
                ContentScreen(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(padding)
                        .verticalScroll(rememberScrollState()),
                    state = state,
                    image = image
                )
            }

            is Error -> {
                ErrorScreen(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) { viewModel.getCurrencyDetailsById(id) }
            }
        }
    }
}

@Composable
private fun ContentScreen(
    modifier: Modifier,
    state: Content,
    image: String,
) {
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 16.dp)
                .clip(CircleShape)
                .size(90.dp)
                .align(Alignment.CenterHorizontally),
            model = image,
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(id = R.string.description_title),
            style = Typography.titleLarge.copy(color = Color.Black)
        )

        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = state.description,
            style = Typography.titleSmall
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(id = R.string.categories_title),
            style = Typography.titleLarge.copy(color = Color.Black)
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = state.categories,
            style = Typography.titleSmall
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CryptoDetailsScreenPreview() {
    CryptoDetailsScreen(
        id = "",
        name = "",
        image = ""
    ) {}
}