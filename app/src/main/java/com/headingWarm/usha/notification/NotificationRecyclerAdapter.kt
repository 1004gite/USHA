package com.headingWarm.usha.notification

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.headingWarm.usha.R
import com.headingWarm.usha.databinding.NotificationItemBinding
import java.util.zip.Inflater

class NotificationRecyclerAdapter(var item: MutableList<Int>)
    : RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationRecyclerViewHolder>() {

    fun refreshItem(item: MutableList<Int>){
        this.item = item
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationRecyclerViewHolder {
        var binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context))
        return NotificationRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NotificationRecyclerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class NotificationRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(){}
    }
}