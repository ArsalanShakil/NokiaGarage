package com.example.mygarage.ui.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentSignInBinding
import com.example.mygarage.ui.utils.ConnectivityCheck
import com.example.mygarage.ui.utils.InternetCheckDialog
import com.example.mygarage.ui.utils.LoadingDialog
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {


    private val signInViewModel: SignInViewModel by koinNavGraphViewModel(R.id.signInFragment)


    private lateinit var binding: FragmentSignInBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_in, container, false
        )
        binding.viewModel = signInViewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val internetCheck =  ConnectivityCheck(requireContext())
        val internetCheckDialog = InternetCheckDialog(requireActivity())
        internetCheckDialog.startLoading()
        internetCheckDialog.isDismiss()
        val loading = LoadingDialog(requireActivity())
        binding.signupTxt.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_signUpFragment
            )
        }
        signInViewModel.signIn.observe(viewLifecycleOwner, {
            if (!signInViewModel.checkResponse(it._id)){
                loading.isDismiss()
                findNavController().navigate(R.id.action_signInFragment_to_navigation_home)
            } else {
                binding.errorTxt.visibility = View.VISIBLE
                binding.errorTxt.text = it.message
            }
        })


            internetCheck.observe(viewLifecycleOwner,{
                if(it == true){
                    internetCheckDialog.isDismiss()
                    binding.signInBtn.setOnClickListener {
                        loading.startLoading()
                        binding.errorTxt.visibility = View.GONE
                        signInViewModel.signInBtnClick()
                    }
                }
                else {
                    internetCheckDialog.startLoading()
                }
            })

    }

}