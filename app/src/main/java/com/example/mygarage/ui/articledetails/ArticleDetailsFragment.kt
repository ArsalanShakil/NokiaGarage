package com.example.mygarage.ui.articledetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_article_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleDetailsFragment : Fragment() {

    val args : ArticleDetailsFragmentArgs by navArgs()


    private val homeViewModel by sharedViewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemPos = args.position
        val color = args.color
        val image = args.image

        homeViewModel.articleList.observe(viewLifecycleOwner, {
            titleTxt.text = it[itemPos].title
            detailTxt.text = it[itemPos].article
            articleDetailsPage.setBackgroundColor(Color.parseColor(color))
            Glide.with(requireContext()).load(image).into(thumbnailImg)
        })
    }

}