package com.example.mygarage.ui.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mygarage.R
import com.example.mygarage.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_article_details.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleDetailsFragment : Fragment() {

    val args : ArticleDetailsFragmentArgs by navArgs()


    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemPos = args.position

        homeViewModel.articleList.observe(viewLifecycleOwner, {
            titleTxt.text = it[itemPos].title
            detailTxt.text = it[itemPos].article
        })
    }

}