package com.example.mygarage.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.R

class ArticleRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var articleTitle = itemView.findViewById<TextView>(R.id.titleTxt)
    var articleImage = itemView.findViewById<ImageView>(R.id.articleImg)
    var constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.constraintLayout)

}