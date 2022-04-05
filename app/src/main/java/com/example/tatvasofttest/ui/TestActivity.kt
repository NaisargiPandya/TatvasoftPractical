package com.example.tatvasofttest.ui

import com.example.tatvasofttest.Response.MainTestResponse
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tatvasofttest.BR
import com.example.tatvasofttest.Base.BaseActivity
import com.example.tatvasofttest.R
import com.example.tatvasofttest.com.example.tatvasofttest.ui.ChooseMainAdapter
import com.example.tatvasofttest.com.example.tatvasofttest.ui.TestNavigator
import com.example.tatvasofttest.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.tatvasofttest.network.Resource

@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>(),TestNavigator {

    var chooseMainList : ArrayList<MainTestResponse.DataList.Users>  = ArrayList()
    lateinit var chooseMainAdapter: ChooseMainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
        mViewModel.getMainList()

    }

    override val layoutId: Int
        get() = R.layout.activity_test
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setupObservable() {

        mViewModel.getTestPlanObservable().observe(this, {

            when (it.status) {
                Resource.Status.SUCCESS -> {

                    Log.e("TAG", "on success=>${it.message}")
                    Toast.makeText(this, "ON success!!", Toast.LENGTH_SHORT).show()

                    it.let {
                        val dataValue = gson.toJson(it.data)
                        if (it.data?.data?.users != null && it.data.data.users!!.size != 0) {
                            chooseMainList.addAll(it.data.data.users!!)
                        }
                        chooseMainAdapter= ChooseMainAdapter(chooseMainList!!)
                        binding.rvAddRecyclerView.adapter = chooseMainAdapter
                        Log.e("TAG", "SUCCESS::${it.status}")
                    }
                }

                Resource.Status.ERROR -> {

                    Log.e(TAG, "on error=>${it.message}")
                    Toast.makeText(this, "ON Error!!", Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    Log.e(TAG, "loading=>${it.message}")
                    Toast.makeText(this, "ON Loading!!", Toast.LENGTH_SHORT).show()

                }

                Resource.Status.NO_INTERNET_CONNECTION -> {
                    Log.e(TAG, "no internet=>${it.message}")
                    Toast.makeText(this, "ON Internet check!!", Toast.LENGTH_SHORT).show()

                }

            }
        })

    }
}