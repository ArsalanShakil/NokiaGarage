package com.example.mygarage.ui.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.databinding.FragmentSignInBinding
import com.example.mygarage.ui.home.HomeViewModel

class SignInFragment : Fragment() {


    private lateinit var signInViewModel: SignInViewModel
    private var _binding: FragmentSignInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInViewModel =
            ViewModelProvider(this).get(SignInViewModel::class.java)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}