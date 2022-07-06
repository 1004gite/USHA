package com.example.usha.community.recyclerview

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.R
import com.example.usha.community.detailpages.CommunityDetailFragment
import com.example.usha.community.model.CommunityItemModel
import com.example.usha.databinding.CommunityItemBinding

class CommunityRecyclerAdapter(private val height: Int, val navController: NavController): RecyclerView.Adapter<CommunityViewHolder>() {

    var tmpList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val itemBinding= CommunityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        itemBinding.root.layoutParams.height = height
        itemBinding.root.setOnClickListener {
            // 번들 데이터 테스트
            val test = CommunityItemModel("1","1","1","1","1")
            val bundle = Bundle().apply {
                putSerializable("test",test)
            }
            navController.navigate(R.id.communityDetail,bundle)
        }
        return CommunityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
        return tmpList.size
    }
}