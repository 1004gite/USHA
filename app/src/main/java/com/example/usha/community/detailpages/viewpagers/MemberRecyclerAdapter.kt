package com.example.usha.community.detailpages.viewpagers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.R
import com.example.usha.databinding.ItemMemberBinding

// 가입 맴버를 불러오는 api가 아직 서버측에서 제공되지 않고 있음
class MemberRecyclerAdapter(val members: List<String>): RecyclerView.Adapter<MemberRecyclerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberRecyclerViewHolder {
        var binding = DataBindingUtil.inflate<ItemMemberBinding>(LayoutInflater.from(parent.context), R.layout.item_member, parent, false)

        return MemberRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberRecyclerViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return members.size
    }
}

class MemberRecyclerViewHolder(binding: ItemMemberBinding): RecyclerView.ViewHolder(binding.root){

}