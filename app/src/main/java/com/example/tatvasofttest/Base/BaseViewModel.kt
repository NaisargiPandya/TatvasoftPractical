package com.example.tatvasofttest.Base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.sql.DataSource

open class BaseViewModel<N>: ViewModel() {

    lateinit var mNavigator: WeakReference<N>
    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }


    @Inject
    lateinit var baseDataSource: DataSource
}
