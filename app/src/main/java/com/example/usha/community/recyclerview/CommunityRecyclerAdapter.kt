package com.example.usha.community.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.R
import com.example.usha.community.model.Community
import com.example.usha.community.model.CommunityItem
import com.example.usha.databinding.CommunityItemBinding

class CommunityRecyclerAdapter(private val height: Int, val navController: NavController, var communityItems: MutableList<LiveData<Community>>): RecyclerView.Adapter<CommunityViewHolder>() {

    val loadingCommunityItem = Community(0,"","","","","","","","","",
        listOf(),"","","","loading~","", listOf(),"","","","","","","")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val itemBinding= CommunityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        itemBinding.root.layoutParams.height = height
        itemBinding.community = loadingCommunityItem
        return CommunityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("info",communityItems[position].value)
            }
            navController.navigate(R.id.communityDetail,bundle)
        }
        holder.bind(communityItems[position].value!!, position)
    }

    override fun getItemCount(): Int {
        return communityItems.size
    }
}