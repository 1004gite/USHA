package com.headingWarm.usha.notification

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.NotificationItemBinding

class NotificationRecyclerAdapter(val viewModel: NotificationViewModel)
    : RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationRecyclerViewHolder>() {

    init {
        // for test
        if(viewModel.item.isEmpty()) viewModel.item.add(1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationRecyclerViewHolder {
        var binding = DataBindingUtil.inflate<NotificationItemBinding>(
            LayoutInflater.from(parent.context), R.layout.notification_item,parent,false)
        return NotificationRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NotificationRecyclerViewHolder, position: Int) {
        Log.e("binding","notiRecycler B")
    }

    override fun getItemCount(): Int {
        return viewModel.item.size
    }

    class NotificationRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(){

        }

    }
}