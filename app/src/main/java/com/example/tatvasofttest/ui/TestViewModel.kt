package com.example.tatvasofttest.ui

import Extention.AppLog
import Response.MainTestResponse
import android.content.ContentValues.TAG
import android.content.Context
import android.provider.SyncStateContract
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tatvasofttest.Base.BaseViewModel
import com.example.tatvasofttest.com.example.tatvasofttest.ui.TestNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import network.ApiConst
import network.NetworkUtils
import network.Resource
import retrofit2.Response
import util.network.NetworkService
import javax.inject.Inject

class TestViewModel @Inject constructor(
    var context: Context,
    var networkService: NetworkService
) : BaseViewModel<TestNavigator>() {

    private val testResponseObservable: MutableLiveData<Resource<MainTestResponse>> =
        MutableLiveData()

    fun getTestPlanObservable(): LiveData<Resource<MainTestResponse>> {
        return testResponseObservable
    }

    fun getMainList() {
        if (NetworkUtils.isNetworkConnected(context)) {
            var response: Response<MainTestResponse>? = null
            viewModelScope.launch {
                testResponseObservable.value = Resource.loading(null)
                withContext(Dispatchers.IO) {

                    response = networkService.mainTest(ApiConst.WEB_API_GET_MAIN_LIST)
                }
                withContext(Dispatchers.Main) {
                    response.run {
                        AppLog.e("SyncStateContract.Constants.TAG", "${baseDataSource.getResult(true) { this!! }}")
                        testResponseObservable.value =
                            baseDataSource.getResult(true) { this!! }
                    }
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    testResponseObservable.value = Resource.noInternetConnection(null)
                }
            }
        }
    }


}




