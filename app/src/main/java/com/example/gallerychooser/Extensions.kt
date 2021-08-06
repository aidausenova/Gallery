package com.example.gallerychooser

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition

fun ImageView.loadImage(url: String?) {
    val imageView = this
    Glide.with(this).asBitmap().load(url).apply(
        RequestOptions().diskCacheStrategy(
            DiskCacheStrategy.RESOURCE
        ).priority(Priority.HIGH)
    ).into(object : BitmapImageViewTarget(this) {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            val v = imageView.width.toDouble() / resource.width.toDouble()
            imageView.layoutParams.height = (resource.height * v).toInt()
            imageView.setImageBitmap(resource)
        }
    })
}