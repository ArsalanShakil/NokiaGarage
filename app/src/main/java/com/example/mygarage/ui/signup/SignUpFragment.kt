package com.example.mygarage.ui.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentSignInBinding
import com.example.mygarage.databinding.FragmentSignUpBinding
import com.example.mygarage.ui.signin.SignInViewModel

class SignUpFragment : Fragment() {

    private lateinit var signUpViewModel: SignUpViewModel
    private var _binding: FragmentSignUpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpViewModel =
            ViewModelProvider(this).get(SignUpViewModel::class.java)

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}