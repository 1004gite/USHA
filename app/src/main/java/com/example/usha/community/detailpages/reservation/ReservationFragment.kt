package com.example.usha.community.detailpages.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.usha.MyApplication
import com.example.usha.R
import com.example.usha.community.model_community.Community
import com.example.usha.databinding.FragmentReservationBinding
import com.example.usha.dialogUtils.DialogUtils

class ReservationFragment : Fragment() {


    private lateinit var viewModel: ReservationViewModel
    private lateinit var binding: FragmentReservationBinding
    private lateinit var mCommunity: Community

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCommunity = arguments?.getSerializable("community") as Community
        val ableTintList = ResourcesCompat.getColorStateList(resources,R.color.ableBtnColor,null)
        val disableTintList = ResourcesCompat.getColorStateList(resources,R.color.disableBtnColor,null)
        viewModel = ViewModelProvider(this,
            ReservationViewModel.ReservationViewModelFactory(
                mCommunity,
                DialogUtils().getWebViewDialog(
                    requireContext(),
                    R.layout.webview_dialog,
                    MyApplication.Consts.reservationTermUrl
                ),
                ableTintList!!,
                disableTintList!!
            )
        ).get(ReservationViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentReservationBinding?>(inflater,R.layout.fragment_reservation,container,false).apply {
            lifecycleOwner = this@ReservationFragment
            viewModel = this@ReservationFragment.viewModel
        }
        return binding!!.root
    }

}