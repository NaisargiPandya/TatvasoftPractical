package com.example.tatvasofttest.com.example.tatvasofttest.ui

import com.example.tatvasofttest.Extention.loadImage
import com.example.tatvasofttest.Response.MainTestResponse
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tatvasofttest.Extention.AppLog
import com.example.tatvasofttest.R
import com.example.tatvasofttest.databinding.TestImageInnerItemBinding
import com.example.tatvasofttest.databinding.TestItemBinding

class ImageAdapter(val imageList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.MenuHolder>() {

    lateinit var context: Context
    lateinit var binding: TestImageInnerItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {

        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.test_image_inner_item, parent, false
        )

        return MenuHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MenuHolder, @SuppressLint("RecyclerView") position: Int) {

        val data = imageList[position]
        binding.ivImageview.loadImage(data)

    }


    override fun getItemCount(): Int {
        return imageList.size
    }

    class MenuHolder(var binding: TestImageInnerItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}