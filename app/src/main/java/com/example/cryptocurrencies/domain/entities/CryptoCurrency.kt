package com.example.cryptocurrencies.domain.entities

import com.google.gson.annotations.SerializedName

data class CryptoCurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)
