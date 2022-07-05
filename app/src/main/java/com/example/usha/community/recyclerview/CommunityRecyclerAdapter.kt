package com.example.usha.community.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.databinding.CommunityItemBinding

class CommunityRecyclerAdapter: RecyclerView.Adapter<CommunityViewHolder>() {

    var tmpList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val itemBinding= CommunityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommunityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
        return tmpList.size
    }
}