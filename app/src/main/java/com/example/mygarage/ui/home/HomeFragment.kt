package com.example.mygarage.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.network.articles.Articles
import com.example.mygarage.network.articles.ArticlesData
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: ArticleRecyclerViewAdapter

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
                    adapter = ArticleRecyclerViewAdapter(requireContext(), articles)
                    articlesRecyclerview.adapter = adapter
                    articlesRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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