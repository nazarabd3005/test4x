package com.nazar.test4x.ui.bindingadapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(view: AppCompatImageView, imageUrl: String){
    Glide.with(view.context).load(imageUrl).into(view)
}