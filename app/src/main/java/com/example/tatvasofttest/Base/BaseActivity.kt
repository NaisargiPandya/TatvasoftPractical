package com.example.tatvasofttest.Base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {
    abstract val layoutId: Int
    abstract val bindingVariable: Int

    @Inject
    lateinit var mViewModel: V
    lateinit var binding: T

    lateinit var activity: Activity

    @Inject
    lateinit var gson: Gson

    abstract fun setupObservable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()
    }

    private fun performDataBinding() {
        activity = this
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(bindingVariable, mViewModel)
        binding.executePendingBindings()
        setupObservable()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //hideKeyboard()
    }

    open fun checkIsConnectionReset(code: Int): Boolean {
        return code == 502
    }

    protected open fun goBack() {
        onBackPressed()
    }

}


