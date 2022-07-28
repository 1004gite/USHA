package com.example.usha.logins

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.usha.MyApplication
import com.example.usha.R
import com.example.usha.databinding.FragmentLoginMainBinding

class LoginMainFragment : Fragment() {

//    companion object {
//        fun newInstance() = LoginMainFragment()
//    }

    private lateinit var viewModel: LogingMainViewModel
    private lateinit var binding: FragmentLoginMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(MyApplication.loginInfo.loginned){
            // 로그인 되어있으면 profile fragment로 이동
            var navops: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.loginMain,true).build()
            this.findNavController().navigate(R.id.profile,null,navops)
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_main,container,false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LogingMainViewModel::class.java)
        viewModel.navController = this.findNavController()

        binding!!.viewModel = viewModel
        return binding!!.root
    }

}