package com.example.movies.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.moviescourse.Model.Review.Review

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    private var reviews = ArrayList<Review>()
    private lateinit var onItemClickListener : OnItemClickListener

    fun setReviews(reviews : List<Review>){
        this.reviews = reviews as ArrayList<Review>
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)

        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]

        holder.tvReviewName.text = review.author
        holder.tvReviewTitle.text = review.title
        holder.tvReviewText.text = review.review

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(review)
        }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvReviewName = itemView.findViewById<TextView>(R.id.tvReviewName)
        val tvReviewTitle = itemView.findViewById<TextView>(R.id.tvReviewTitle)
        val tvReviewText = itemView.findViewById<TextView>(R.id.tvReviewText)
    }

    interface OnItemClickListener{
        fun onItemClick(review : Review)
    }
}