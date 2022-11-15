package com.headingWarm.usha.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.FragmentRegisterBinding
import com.headingWarm.usha.dialogUtils.DialogUtils

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var viewModel: RegisterViewModel
    val dialUtil = DialogUtils()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentRegisterBinding?>(inflater, R.layout.fragment_register,container, false)
            .apply { lifecycleOwner = this@RegisterFragment }

        viewModel = with(RegisterViewModel.RegisterViewModelFac(RegisterModel(findNavController()))){
            ViewModelProvider(this@RegisterFragment,this).get(RegisterViewModel::class.java)
        }

        binding.viewModel = viewModel

        viewModel.model.termUrl.observe(viewLifecycleOwner) {
            dialUtil.getWebViewDialog(context!!, it).show()
        }

        return binding.root
    }

}