package com.headingWarm.usha.notification

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.NotificationItemBinding

class NotificationRecyclerAdapter(var item: MutableList<Int>)
    : RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationRecyclerViewHolder>() {

    fun changeItem(item: MutableList<Int>){
        this.item = item
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
        return item.size
    }

    class NotificationRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(){

        }

    }
}