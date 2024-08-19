package com.example.cryptocurrencies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencies.App
import com.example.cryptocurrencies.navigation.NavigationHost
import com.example.cryptocurrencies.presentation.ViewModelFactory
import com.example.cryptocurrencies.ui.theme.CryptoCurrenciesTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        setContent {
            CryptoCurrenciesTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                val navController by rememberUpdatedState(newValue = rememberNavController())
                NavigationHost(
                    navHostController = navController,
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}