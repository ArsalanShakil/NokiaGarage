package com.example.mygarage.ui.home

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.articles.ArticlesDataItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleRecyclerViewAdapter(val context: Context, val articles: List<ArticlesDataItem>) : RecyclerView.Adapter<ArticleRecyclerViewHolder>() {

    var mColors = arrayOf(
        "#D0F1EB", "#FDC9D2", "#D3E1FF", "#F9E8BD", "#BDEEF9", "#BDF9EB"
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.articles_recycler_item,parent, false)
        return ArticleRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleRecyclerViewHolder, position: Int) {
        val articles = articles[position]
        holder.articleTitle.text = articles.title
        Glide.with(context).load(articles.thumbnail).into(holder.articleImage)
        val index: Int = position % mColors.size
        holder.constraintLayout.setBackgroundColor(Color.parseColor(mColors[index]))
        holder.itemView.setOnClickListener{ view ->
            val action = HomeFragmentDirections.actionNavigationHomeToArticleDetailsFragment(position)
            view.findNavController().navigate(action)
            Log.d("pos",position.toString())
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }


}
