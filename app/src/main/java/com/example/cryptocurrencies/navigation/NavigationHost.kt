package com.example.cryptocurrencies.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.cryptocurrencies.ui.screens.CryptoDetailsScreen
import com.example.cryptocurrencies.ui.screens.CryptoListScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory
) {
    NavHost(navController = navHostController, startDestination = CryptoCurrencyListScreen) {
        composable<CryptoCurrencyListScreen> {
            CryptoListScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel(factory = viewModelFactory)
            ) { id: String, name: String, image: String ->
                navHostController.navigate(CryptoCurrencyDetailsScreen(id, name, image))
            }
        }
        composable<CryptoCurrencyDetailsScreen> {
            val args = it.toRoute<CryptoCurrencyDetailsScreen>()
            CryptoDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel(factory = viewModelFactory),
                id = args.id,
                name = args.name,
                image = args.image
            ) {
                navHostController.popBackStack()
            }
        }
    }
}