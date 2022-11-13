package com.headingWarm.usha.logins

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.FragmentLoginMainBinding

class LoginMainFragment : Fragment() {

    private lateinit var viewModel: LoginMainViewModel
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

        viewModel = ViewModelProvider(this, LoginMainViewModel.LoginMainVMFac(findNavController())).get(LoginMainViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentLoginMainBinding?>(inflater,R.layout.fragment_login_main,container,false).apply {
            lifecycleOwner = this@LoginMainFragment
            viewModel = this@LoginMainFragment.viewModel
        }

        showAlertDialog("경고","로그인이 필요합니다.")

        return binding.root
    }

    fun showAlertDialog(title:String, text: String){
        AlertDialog.Builder(context)
            .setTitle(title)
            .setPositiveButton("확인", null)
            .create()
            .apply {
                setView(TextView(context).apply {
                    setText(text)
                    setPadding(50)
                })
                show()
            }
    }


}