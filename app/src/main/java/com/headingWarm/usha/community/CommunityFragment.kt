package com.headingWarm.usha.community

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.headingWarm.usha.R
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.CommunityItemBinding
import com.headingWarm.usha.databinding.FragmentCommunityBinding

class CommunityFragment() : Fragment() {

    lateinit var binding: FragmentCommunityBinding
    private lateinit var viewModel: CommunityViewModel
    private lateinit var adapter: CommunityRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(CommunityViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentCommunityBinding>(inflater, R.layout.fragment_community, container, false).apply {
            lifecycleOwner = this@CommunityFragment
            viewModel = this@CommunityFragment.viewModel
        }

        adapter = CommunityRecyclerAdapter(resources.displayMetrics.heightPixels / 2,
            findNavController(),
            viewModel.communityItems)
        binding.communityRecyclerView.adapter=adapter

        // model의 communityItem이 바뀌면 adapter를 갱신해준다.
        viewModel.communityItems.observe(viewLifecycleOwner){ adapter.refreshItem() }

        return binding.root
    }

    class CommunityRecyclerAdapter(private val height: Int, val navController: NavController, val communityItems: LiveData<List<Community>>): RecyclerView.Adapter<CommunityRecyclerAdapter.CommunityViewHolder>() {

        var item = listOf<Community>()
        fun refreshItem(){
            item = communityItems.value!!
            this.notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
            val itemBinding= CommunityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            itemBinding.root.layoutParams.height = height
            return CommunityViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable("community",item[position])
                }
                navController.navigate(R.id.communityDetail,bundle)
            }
            holder.bind(item[position])
        }

        override fun getItemCount(): Int {
            return item.size
        }

        class CommunityViewHolder(private val itemBinding: CommunityItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
            fun bind(community: Community) {
                // 데이터바인딩을 위함
                itemBinding.community = community
            }
        }
    }

}