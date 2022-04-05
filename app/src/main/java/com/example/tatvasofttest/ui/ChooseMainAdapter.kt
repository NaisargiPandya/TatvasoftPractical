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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MenuHolder, @SuppressLint("RecyclerView") position: Int) {

        val test = testList[position]
        setData(holder, test.image)

        AppLog.e( "TAG","name-> ${test.name}")
        AppLog.e( "TAG","image -> ${test.image}")
       /* if (testList.size % 2 == 0){
            val test = testList[position]
            setData(holder,test.image,test.name)
        }else{
            val testItem= testListItem[position]
            setData(holder,testItem.items,"")

        }*/

    }

    private fun setData(holder: MenuHolder, resource: String) {
        holder.binding.evenImg1.loadImage(resource)


        /*holder.binding.oddImg1.loadImage(resource)
        holder.binding.oddImg2.loadImage(resource)
        holder.binding.oddImg3.loadImage(resource)

        holder.binding.oddText1.text= serviceName
        holder.binding.oddText2.text= serviceName
        holder.binding.oddText3.text= serviceName*/


    }


    override fun getItemCount(): Int {
        return testList.size
    }

    class MenuHolder(var binding: TestItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
