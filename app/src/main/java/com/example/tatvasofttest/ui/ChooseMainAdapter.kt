package com.example.tatvasofttest.com.example.tatvasofttest.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tatvasofttest.Extention.loadImage
import com.example.tatvasofttest.R
import com.example.tatvasofttest.Response.MainTestResponse
import com.example.tatvasofttest.databinding.TestItemBinding

class ChooseMainAdapter(val testList: MutableList<MainTestResponse.DataList.Users>) :
    RecyclerView.Adapter<ChooseMainAdapter.MenuHolder>() {

    lateinit var context: Context
    lateinit var binding: TestItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {

        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.test_item,
            parent,
            false
        )

        return MenuHolder(binding)
    }

    fun addAll(list: List<MainTestResponse.DataList.Users>) {
        testList.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MenuHolder, @SuppressLint("RecyclerView") position: Int) {

        val test = testList[position]
        val itemList = test.items
        binding.evenText1.text = test.name
        binding.imageView.loadImage(test.image)
        if (testList.size % 2 == 0) {
            holder.binding.ivImageview.visibility = View.GONE
        }else{
            holder.binding.ivImageview.loadImage(itemList[0])
            holder.binding.ivImageview.visibility = View.VISIBLE
            itemList.drop(0)
        }

        val adapter = ImageAdapter(itemList)
        binding.rvAddRecyclerView.adapter = adapter

    }

    override fun getItemCount(): Int {
        return testList.size
    }

    class MenuHolder(var binding: TestItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
