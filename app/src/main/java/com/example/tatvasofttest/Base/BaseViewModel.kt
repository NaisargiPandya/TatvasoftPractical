package com.example.tatvasofttest.Base

import androidx.lifecycle.ViewModel
import util.network.ResourceDataSoruce
import java.lang.ref.WeakReference
import javax.inject.Inject


open class BaseViewModel<N>: ViewModel() {

    lateinit var mNavigator: WeakReference<N>
    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }


    @Inject
    lateinit var baseDataSource: ResourceDataSoruce
}
