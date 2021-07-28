package com.app.compare_my_trade.utills

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.compare_my_trade.R
import com.bumptech.glide.Glide

object Singleton {
    var isNetworkConnected: Boolean = false
    var StatusCode: String = ""
    var mImdb:String=""
    var BaseImageUrl="https://image.tmdb.org/t/p/w185/"

    @BindingAdapter("imageUrls")
    @JvmStatic
    fun imageUrls(view: ImageView, imageUrl: String?) {
        if (imageUrl != null && imageUrl != "")
            Glide.with(view.context)
                .load(BaseImageUrl+imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
        else
            Glide.with(view.context)
                .load(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
    }

    fun navigateTo(context: Context, clazz: Class<*>, mExtras: Bundle) {
        val intent = Intent(context, clazz)
        intent.putExtras(mExtras)
        context.startActivity(intent)
    }


}