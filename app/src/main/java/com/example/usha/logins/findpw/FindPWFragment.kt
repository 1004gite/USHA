package com.example.usha.logins.findpw

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.usha.R
import com.example.usha.databinding.FragmentFindPWBinding

class FindPWFragment : Fragment() {

    lateinit var binding: FragmentFindPWBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_find_p_w,container,false)

        binding.btnSend.setOnClickListener {
            showAlertDialog("오류", "현재 서비스 준비중 입니다. 고객센터로 연락 주시면 바로 도와드리겠습니다.")
        }
        
        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }

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
                    gravity = Gravity.CENTER
                })
                show()
            }
    }
}