package com.example.usha.community.detailpages.viewpagers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.R
import com.example.usha.community.model_community.Review
import com.example.usha.databinding.ItemReviewBinding

class ReviewRecyclerAdapter(val reviews: List<Review>): RecyclerView.Adapter<ReviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = DataBindingUtil.inflate<ItemReviewBinding>(LayoutInflater.from(parent.context), R.layout.item_review, parent, false)

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindReview(reviews[position])
    }

    override fun getItemCount(): Int {
        return reviews.size
    }


}

class ReviewViewHolder(var binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bindReview(review: Review){
        binding.review = review
    }
}