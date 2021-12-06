package com.example.mygarage.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentEquipmentBinding
import com.example.mygarage.databinding.FragmentProfileBinding
import com.example.mygarage.ui.equipment.EquipmentViewModel
import com.example.mygarage.ui.home.HomeViewModel
import com.example.mygarage.ui.signin.SignInViewModel
import com.example.mygarage.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class ProfileFragment : Fragment() {


    private val profileViewModel by viewModel<ProfileViewModel>()
    private val signInViewModel: SignInViewModel by sharedViewModel()
    private val signUpViewModel: SignUpViewModel by sharedViewModel()
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_signInFragment)
        }

        signInViewModel.signIn.observeForever {
           profileViewModel.setText(it._id)
        }
        signUpViewModel.signUp.observeForever {
            profileViewModel.setText(it._id)
        }
        profileViewModel.profle.observe(viewLifecycleOwner,{
            Log.d("Profile", it.fullName)
            binding.usernameTxt.text = it.fullName
            binding.emailTxt.text = it.email
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}