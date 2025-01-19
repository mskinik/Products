package com.mskinik.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mskinik.products.R

@BindingAdapter("image", "placeholder")
fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
    if (url.isNullOrEmpty().not()) {
        Glide.with(image.context).load(url).centerCrop()
            .into(image)
    } else {
        image.setImageDrawable(placeHolder)
    }
}