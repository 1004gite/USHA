package com.headingWarm.usha.community.detailpages.viewpagers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.headingWarm.usha.Consts
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.R
import com.headingWarm.usha.community.item_community.Review
import com.headingWarm.usha.databinding.ItemReviewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewRecyclerAdapter(val reviews: List<Review>, val communityId: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0) return EmptyViewHolder(View(parent.context).apply { minimumHeight = 300 })

        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context))
        val vh = ReviewViewHolder(binding)
        binding.root.setOnLongClickListener { v ->
            showPopUp(parent.context, v, vh.adapterPosition)
            true
        }
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if( holder is ReviewViewHolder ) (holder as ReviewViewHolder).bindReview(reviews[position])
    }

    override fun getItemCount(): Int {
        // 마지막에 빈 공산을 넣기 위함
        return reviews.size+1
    }

    override fun getItemViewType(position: Int): Int {
        if(position == reviews.size) return 0
        return 1
    }

    class EmptyViewHolder(v: View):RecyclerView.ViewHolder(v){

    }

    class ReviewViewHolder(var binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindReview(review: Review) {
            binding.review = review
        }
    }

    fun showPopUp(context: Context, view: View, position: Int) {
        PopupMenu(context, view, Gravity.RIGHT).apply {
            menuInflater.inflate(R.menu.menu_review_clicked, this.menu)
            setOnMenuItemClickListener { m ->
                when (m.itemId) {
                    R.id.reportReview -> {
                        try {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "plain/text"
                                setPackage("com.google.android.gm")
                                putExtra(Intent.EXTRA_EMAIL, arrayOf(Consts.companyMail))
                                putExtra(Intent.EXTRA_SUBJECT, Consts.rePortMailTitle)
                                putExtra(Intent.EXTRA_TEXT, Consts.getReportString(communityId, position))
                            }
                            context.startActivity(intent)
                        }catch (e: Exception){
                            MyApplication.showToast("Please install Gmail App")
                        }
                    }
                }
                true
            }
        }.show()
    }
}