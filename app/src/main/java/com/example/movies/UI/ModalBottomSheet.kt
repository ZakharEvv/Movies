package com.example.movies.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.movies.R
import com.example.moviescourse.Model.Review.Review
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {

    private lateinit var tvName : TextView
    private lateinit var tvTitle : TextView
    private lateinit var tvText : TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.modal_bottom_sheet_content, container, false)
        initViews(view)
        val review = arguments?.getSerializable("review") as Review

        tvName.text = review.author
        tvTitle.text = review.title
        tvText.text = review.review

        return view
    }

    fun initViews(view : View){
        tvName = view.findViewById(R.id.tvBottomSheetName)
        tvTitle = view.findViewById(R.id.tvBottomSheetTitle)
        tvText = view.findViewById(R.id.tvBottomSheetText)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}