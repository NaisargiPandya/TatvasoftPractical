package com.example.tatvasofttest.Extention

import android.util.Log
import com.example.tatvasofttest.BuildConfig

object AppLog {

    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun d(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg!!)
        }
    }
}