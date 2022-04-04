package com.example.tatvasofttest.ui

import Response.MainTestResponse
import android.os.Bundle
import android.util.Log
import com.example.tatvasofttest.BR
import com.example.tatvasofttest.Base.BaseActivity
import com.example.tatvasofttest.R
import com.example.tatvasofttest.com.example.tatvasofttest.ui.ChooseMainAdapter
import com.example.tatvasofttest.com.example.tatvasofttest.ui.TestNavigator
import com.example.tatvasofttest.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint
import network.Resource

@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>(),TestNavigator {

    var chooseMainList : ArrayList<MainTestResponse>  = ArrayList()
    lateinit var chooseAdapter: ChooseMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)

    }

    override val layoutId: Int
        get() = R.layout.activity_test
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setupObservable() {
        mViewModel.getMainList().observe(this, {

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.commonProgressButton.setLoading(false, userPreference)

                    Log.e(TAG, "on success=>${it.message}")

                    it.let {
                        val data = gson.toJson(it.data)
                        choosePlanList = it.data!!.plansList
                        choosePlanAdapter= ChoosePlanAdapter(navigator,choosePlanList, language)
                        binding.rvServiceBox.adapter = choosePlanAdapter
                        Log.e("TAG", "SUCCESS::${it.status}")
                    }
                }

                Resource.Status.ERROR -> {
                    binding.commonProgressButton.setLoading(false, userPreference)

                    Log.e(TAG, "on error=>${it.message}")
                    if (!checkIsSessionOut(it.code)) {
                        it.message?.let { message ->
                            alertDialog(
                                message = if (checkIsConnectionReset(it.code)) {
                                    getString(R.string.connection_reset)
                                } else {
                                    message.toString()
                                }
                            )
                        }
                    }
                }

                Resource.Status.LOADING -> {
                    Log.e(TAG, "loading=>${it.message}")
                }

                Resource.Status.NO_INTERNET_CONNECTION -> {
                    Log.e(TAG, "no internet=>${it.message}")
                    isInternetConnected = false
                    showInternetDialog(false)
                }

                Resource.Status.SHIMMER_VIEW -> {

                }

            }
        })

    }
}