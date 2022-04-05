package com.example.tatvasofttest.ui

import com.example.tatvasofttest.Response.MainTestResponse
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tatvasofttest.Base.BaseViewModel
import com.example.tatvasofttest.com.example.tatvasofttest.ui.TestNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.tatvasofttest.network.ApiConst
import com.example.tatvasofttest.network.NetworkUtils
import com.example.tatvasofttest.network.Resource
import retrofit2.Response
import com.example.tatvasofttest.network.NetworkService
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
        if (NetworkUtils.isNetworkConnected(context)){
            var response: Response<MainTestResponse>? = null
            viewModelScope.launch {
                testResponseObservable.value = Resource.loading(null)
                withContext(Dispatchers.IO) {

                    response = networkService.mainTest(ApiConst.WEB_API_GET_MAIN_LIST)
                }
                withContext(Dispatchers.Main) {
                    response.run {
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




