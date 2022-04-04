package com.example.tatvasofttest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

lateinit var instanceApp : ApplicationMy

@HiltAndroidApp
class ApplicationMy :Application() {
    override fun onCreate() {
        super.onCreate()
        instanceApp = this

    }
}

