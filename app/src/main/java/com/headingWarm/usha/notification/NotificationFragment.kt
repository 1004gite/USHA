package com.headingWarm.usha.notification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var viewModel: NotificationViewModel
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var adapter: NotificationRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        adapter = NotificationRecyclerAdapter(viewModel.item)
        binding = DataBindingUtil.inflate<FragmentNotificationBinding>(inflater,R.layout.fragment_notification,container,false).apply {
            viewModel = this@NotificationFragment.viewModel
            notificationRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
            notificationRecyclerView.adapter = this@NotificationFragment.adapter
        }

        return binding!!.root
    }

}