package com.example.mygarage.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.ui.home.adapters.ArticleRecyclerViewAdapter
import com.example.mygarage.ui.home.adapters.BookingRecyclerViewAdapter
import com.example.mygarage.ui.signin.SignInViewModel
import com.example.mygarage.ui.signup.SignUpViewModel
import com.example.mygarage.ui.utils.ConnectivityCheck
import com.example.mygarage.ui.utils.InternetCheckDialog
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    private val homeViewModel by sharedViewModel<HomeViewModel>()
    private val signInViewModel: SignInViewModel by koinNavGraphViewModel(R.id.signInFragment)
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
        val internetCheck =  ConnectivityCheck(requireContext())
        val internetCheckDialog = InternetCheckDialog(requireActivity())
        internetCheckDialog.startLoading()
        internetCheckDialog.isDismiss()
        binding.articleShimmerLayout.startShimmer()
        binding.bookingShimmerLayout.startShimmer()


        internetCheck.observe(viewLifecycleOwner,{
            if(it == true){
                internetCheckDialog.isDismiss()
                homeViewModel.getArticles()
                signInViewModel.signIn.observeForever {
                    homeViewModel.getUserBookings(it._id)
                }
                signUpViewModel.signUp.observeForever {
                    homeViewModel.getUserBookings(it._id)
                }
            }
            else {
                internetCheckDialog.startLoading()
            }
        })


        homeViewModel.articleList.observe(viewLifecycleOwner, {
            binding.articleShimmerLayout.apply {
                hideShimmer()
                visibility = View.GONE
            }
            articleAdapter = ArticleRecyclerViewAdapter(requireContext(), it)
            articlesRecyclerview.adapter = articleAdapter
            articlesRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        })



        homeViewModel.bookingList.observe(viewLifecycleOwner, {
            binding.bookingShimmerLayout.apply {
                hideShimmer()
                visibility = View.GONE
            }
            if(it.isEmpty()){
                bookingsRecyclerview.visibility = View.GONE
                noUserReservationsTxt.visibility = View.VISIBLE
            }
            else {
                noUserReservationsTxt.visibility = View.GONE
                bookingsRecyclerview.visibility = View.VISIBLE
                bookingAdapter = BookingRecyclerViewAdapter(requireContext(), it)
                bookingsRecyclerview.adapter = bookingAdapter
                bookingsRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            }

        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


