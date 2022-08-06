package com.example.usha.community.recyclerview


import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.R
import com.example.usha.community.CommunityFragment
import com.example.usha.community.model.Community
import com.example.usha.community.model.CommunityItem
import com.example.usha.databinding.CommunityItemBinding

class CommunityViewHolder(private val itemBinding: CommunityItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(community: Community, position: Int) {
//        itemBinding.root.clipToOutline = true
//        itemBinding.imageView.clipToOutline = true // 배경에 맞게 사진 짜르기
        // 데이터바인딩을 위함
        itemBinding.community = community
    }

}