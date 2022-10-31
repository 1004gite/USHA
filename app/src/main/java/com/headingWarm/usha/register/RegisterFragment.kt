package com.headingWarm.usha.register

import android.os.Bundle
import android.util.Log
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
import io.reactivex.rxjava3.subjects.PublishSubject

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var viewModel: RegisterViewModel
    val dialogPublisher = PublishSubject.create<String>()
    val dialUtil = DialogUtils()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dialogPublisher.subscribe(
            { dialUtil.getWebViewDialog(context!!,R.layout.webview_dialog, it).show() },
            {Log.e("webViewErr", it.message.toString())}
        )

        binding = DataBindingUtil.inflate<FragmentRegisterBinding?>(inflater, R.layout.fragment_register,container, false)
            .apply { lifecycleOwner = this@RegisterFragment }
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java).apply {
            dialogPublisher = this@RegisterFragment.dialogPublisher
            navController = this@RegisterFragment.findNavController()
        }
        binding.viewModel = viewModel

        return binding.root
    }

}