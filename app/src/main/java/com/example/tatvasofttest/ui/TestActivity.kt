package com.example.tatvasofttest.ui

import android.os.Bundle
import com.example.tatvasofttest.BR
import com.example.tatvasofttest.Base.BaseActivity
import com.example.tatvasofttest.R
import com.example.tatvasofttest.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val layoutId: Int
        get() = R.layout.activity_test
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setupObservable() {
        TODO("Not yet implemented")
    }
}