package com.example.stateflow.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide.get
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import java.io.File

internal object ImageLoader {

    fun loadImage(context: Context, url: String, imageView: ImageView, radius: Int = 1) {
        val into = with(context)
            .load(url)
            .fitCenter()
            .transition(withCrossFade())
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
            .into(imageView)
    }

    fun loadImage(context: Context, file: File, imageView: ImageView, radius: Int = 1) {
        with(context)
            .load(file)
            .transition(withCrossFade())
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
            .into(imageView)
    }

    fun loadImage(context: Context, uri: Uri, imageView: ImageView, radius: Int = 1) {
        with(context)
            .load(uri)
            .transition(withCrossFade())
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
            .into(imageView)
    }

    fun loadImage(context: Context, resId: Int, imageView: ImageView, radius: Int = 1) {
        with(context)
            .load(resId)
            .transition(withCrossFade())
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
            .into(imageView)
    }

    fun loadImageWithoutTransform(context: Context, uri: Uri, imageView: ImageView) {
        with(context)
            .load(uri)
            .transition(withCrossFade())
            .into(imageView)
    }

    fun loadImageWithCircularCrop(context: Context, url: String, imageView: ImageView) {
        with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .transition(withCrossFade())
            .into(imageView)
    }

    fun removeCache(context: Context) {
        get(context).clearMemory()
    }
}