package com.jhwasghb.newstestapi.adapters

import android.net.Uri
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhwasghb.newstestapi.R

class BindingAdapter {
    companion object{
        @BindingAdapter("app:rvLayoutManager")
        @JvmStatic
        fun setRecyclerLayoutManager(view:RecyclerView, manager: RecyclerView.LayoutManager){
            view.layoutManager = manager
        }

        @JvmStatic
        @BindingAdapter("app:imageLoad")
        fun loadImage(view :ImageView, url :String?){
            Glide.with(view.context).load(Uri.parse(url)).error(R.mipmap.ic_launcher).into(view)
            //view.setImageResource(R.mipmap.ic_launcher)
        }
    }
}