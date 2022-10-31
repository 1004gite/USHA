package com.headingWarm.usha.community.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.headingWarm.usha.R
import com.headingWarm.usha.community.model_community.Community
import com.headingWarm.usha.databinding.CommunityItemBinding

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
                putSerializable("community",communityItems[position].value)
            }
//            navController.navigate(R.id.action_community_to_communityDetail, bundle)
            navController.navigate(R.id.communityDetail,bundle)
        }
        holder.bind(communityItems[position].value!!, position)
    }

    override fun getItemCount(): Int {
        return communityItems.size
    }
}