package com.example.cryptocurrencies

import android.app.Application
import com.example.cryptocurrencies.di.DaggerAppComponent

class App : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }
}