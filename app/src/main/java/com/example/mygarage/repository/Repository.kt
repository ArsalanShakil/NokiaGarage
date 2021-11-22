package com.example.mygarage.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.network.articles.Articles
import com.example.mygarage.network.articles.ArticlesApi
import com.example.mygarage.network.articles.ArticlesData


class Repository(private val articlesApi : ArticlesApi) {
    private val articlesLiveData = MutableLiveData<ArticlesData>()
    val articles : LiveData<ArticlesData>
    get() = articlesLiveData

     suspend fun getArticles() {
        val articleResult = articlesApi.getArticles()
        if (articleResult?.body() != null) {
            articlesLiveData.postValue(articleResult.body())
        }

    }

}