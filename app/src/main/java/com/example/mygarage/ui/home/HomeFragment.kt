package com.example.mygarage.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.ui.home.adapters.ArticleRecyclerViewAdapter
import com.example.mygarage.ui.home.adapters.BookingRecyclerViewAdapter
import com.example.mygarage.ui.signin.SignInViewModel
import com.example.mygarage.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    private val homeViewModel by viewModel<HomeViewModel>()
    private val signInViewModel: SignInViewModel by sharedViewModel()
    private val signUpViewModel: SignUpViewModel by sharedViewModel()

    private var _binding: FragmentHomeBinding? = null
    lateinit var articleAdapter: ArticleRecyclerViewAdapter
    lateinit var bookingAdapter: BookingRecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.articleList.observe(viewLifecycleOwner, {
            articleAdapter = ArticleRecyclerViewAdapter(requireContext(), it)
            articlesRecyclerview.adapter = articleAdapter
            articlesRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        })


        signInViewModel.signIn.observeForever {
            Log.d("BOOKINGID-SIGNIN", it.toString())
            //homeViewModel.id.postValue(it._id)
            homeViewModel.getUserBookings(it._id)
        }
        signUpViewModel.signUp.observeForever {
            Log.d("BOOKINGID-SIGNUP", it._id)
            homeViewModel.getUserBookings(it._id)
        }
        homeViewModel.bookingList.observe(viewLifecycleOwner, {

            bookingAdapter = BookingRecyclerViewAdapter(requireContext(), it)
            bookingsRecyclerview.adapter = bookingAdapter
            bookingsRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


