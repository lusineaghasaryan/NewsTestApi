package com.jhwasghb.newstestapi.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jhwasghb.newstestapi.R
import com.jhwasghb.newstestapi.databinding.ItemNewsLayoutBinding
import com.jhwasghb.newstestapi.models.ArticlesItem

class NewsRecyclerViewAdapter(var context: Context, var newsList: List<ArticlesItem>) :
    RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    var itemClick: (position: Int, item: ArticlesItem) -> Unit = {position, item -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ItemNewsLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.item_news_layout, parent, false)

        Log.d("Teams", "-----------------------ttttt------------------------------")
        return NewsViewHolder( binding)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
        holder.itemClick = itemClick
    }
    fun setNews(newsList111: List<ArticlesItem>){
        newsList = newsList111
        notifyDataSetChanged()
    }


    class NewsViewHolder(val binding: ItemNewsLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var news: ArticlesItem

        var itemClick: (position: Int, item: ArticlesItem) -> Unit = {position, item -> }

        fun bind(item: ArticlesItem) {
            binding.holder = this
            news = item
            binding.currentData = news
        }

        fun onItemClick(view: View){
            itemClick(adapterPosition, news)
        }
    }
}