package com.example.mygarage.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.network.articles.Articles
import com.example.mygarage.network.articles.ArticlesApi
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.articles.ArticlesDataItem
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArticles()
    }

    private fun getArticles() {
        val articles = Articles.articlesInstance.getArticles()
        articles.enqueue(object : retrofit2.Callback<ArticlesData>{
            override fun onResponse(call: Call<ArticlesData>, response: Response<ArticlesData>) {
                val articles = response.body()
                if (articles != null) {
                    Log.d("Response", articles.toString())
                }
            }

            override fun onFailure(call: Call<ArticlesData>, t: Throwable) {
                Log.d("Articles Error","Error in fetching articles", t)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}