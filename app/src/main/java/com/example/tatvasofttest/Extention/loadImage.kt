package com.example.tatvasofttest.Extention

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.tatvasofttest.R

fun ImageView.loadImage(path: String, placeholder: Int = R.drawable.ic_gallery) {
    Glide.with(context)
        .load(path)
        .placeholder(placeholder)
        .error(placeholder)
        .into(this)
}