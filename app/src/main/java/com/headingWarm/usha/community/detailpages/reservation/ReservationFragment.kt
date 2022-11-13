package com.headingWarm.usha.community.detailpages.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.R
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.FragmentReservationBinding
import com.headingWarm.usha.dialogUtils.DialogUtils

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
            ReservationViewModel.ReservationViewModelFactory(ReservationModel(
                mCommunity,
                DialogUtils().getWebViewDialog(requireContext(), "https://www.headingwarm.com/term"),
                ableTintList!!,
                disableTintList!!,
                findNavController())
            )
        ).get(ReservationViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentReservationBinding?>(inflater,R.layout.fragment_reservation,container,false).apply {
            lifecycleOwner = this@ReservationFragment
            viewModel = this@ReservationFragment.viewModel
        }
        return binding.root
    }

}