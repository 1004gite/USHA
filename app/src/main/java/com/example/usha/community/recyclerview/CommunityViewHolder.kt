package com.example.usha.community.recyclerview


import androidx.recyclerview.widget.RecyclerView
import com.example.usha.community.model_community.Community
import com.example.usha.databinding.CommunityItemBinding

class CommunityViewHolder(private val itemBinding: CommunityItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(community: Community, position: Int) {
//        itemBinding.root.clipToOutline = true
//        itemBinding.imageView.clipToOutline = true // 배경에 맞게 사진 짜르기
        // 데이터바인딩을 위함
        itemBinding.community = community
    }

}