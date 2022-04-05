package com.example.tatvasofttest.ui

import com.example.tatvasofttest.Response.MainTestResponse
import android.content.ContentValues.TAG
import android.nfc.tech.MifareUltralight
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private var loading = true
    private var isLastPage = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
        mViewModel.getMainList()

        chooseMainAdapter= ChooseMainAdapter(chooseMainList)
        binding.rvAddRecyclerView.adapter = chooseMainAdapter

        val mLayoutManager = binding.rvAddRecyclerView.layoutManager!! as LinearLayoutManager
        binding.rvAddRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = mLayoutManager.childCount
                val totalItemCount: Int = mLayoutManager.itemCount
                val firstVisibleItemPosition: Int = mLayoutManager.findFirstVisibleItemPosition()
                if (!loading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= MifareUltralight.PAGE_SIZE) {
                        if (!chooseMainList.isNullOrEmpty()) {
                            mViewModel.getMainList()
                        }
                    }
                }
            }
        })
    }

    override val layoutId: Int
        get() = R.layout.activity_test
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setupObservable() {

        mViewModel.getTestPlanObservable().observe(this, {

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loading = false
                    Log.e("TAG", "on success=>${it.message}")
                    Toast.makeText(this, "ON success!!", Toast.LENGTH_SHORT).show()

                    it.let {
                        val dataValue = gson.toJson(it.data)
                        if (it.data?.data?.users != null && it.data.data.users!!.size != 0) {
                            chooseMainList.addAll(it.data.data.users!!)
                        }
                        chooseMainAdapter.addAll(chooseMainList)
                        Log.e("TAG", "SUCCESS::${it.status}")
                    }
                }

                Resource.Status.ERROR -> {
                    loading = false
                    Log.e(TAG, "on error=>${it.message}")
                    Toast.makeText(this, "ON Error!!", Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    Log.e(TAG, "loading=>${it.message}")
                    Toast.makeText(this, "ON Loading!!", Toast.LENGTH_SHORT).show()

                }

                Resource.Status.NO_INTERNET_CONNECTION -> {
                    loading = false
                    Log.e(TAG, "no internet=>${it.message}")
                    Toast.makeText(this, "ON Internet check!!", Toast.LENGTH_SHORT).show()

                }

            }
        })

    }
}