package com.example.mygarage.ui.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.databinding.FragmentSignInBinding
import com.example.mygarage.ui.home.ArticleRecyclerViewAdapter
import com.example.mygarage.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {


    private val signInViewModel by viewModel<SignInViewModel>()

    private var _binding: FragmentSignInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupTxt.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_signUpFragment
            )
        }
        binding.signInBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_navigation_home
            )
        }

        signInViewModel.signIn.observe(viewLifecycleOwner, {
            Log.d("SignIn", it.toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}