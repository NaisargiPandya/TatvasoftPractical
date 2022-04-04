package com.example.tatvasofttest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

lateinit var instanceApp : MyApplication

@HiltAndroidApp
class MyApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        instanceApp = this

    }
}