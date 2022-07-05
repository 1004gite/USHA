package com.example.usha.community.recyclerview


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.databinding.CommunityItemBinding

class CommunityViewHolder(itemBinding: CommunityItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener {
            Log.e("clicked","test")
        }
    }

}