package com.example.pro_todo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.model.Cate


class FirstCateViewHolder(private val binding: CateViewBinding,
                          private val items: List<Cate>,
): RecyclerView.ViewHolder(binding.root) {


    fun bind(cate: Cate){
        binding.ivIc.setImageResource(cate.icon)
        
    }

}