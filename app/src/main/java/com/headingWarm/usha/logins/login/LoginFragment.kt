package com.headingWarm.usha.logins.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentLoginBinding?>(inflater,R.layout.fragment_login,container,false).apply {
            lifecycleOwner = this@LoginFragment.viewLifecycleOwner
        }

        viewModel = ViewModelProvider(
            this,
            LoginViewModel.LoginVMFac(LoginModel(findNavController()))
        ).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        return binding!!.root
    }
}