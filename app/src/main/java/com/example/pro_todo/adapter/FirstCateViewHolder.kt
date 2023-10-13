package com.example.pro_todo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.model.Category


class FirstCateViewHolder(private val binding: CateViewBinding,
                          private val items: List<Category>,
): RecyclerView.ViewHolder(binding.root) {


    fun bind(category: Category){
        binding.ivIc.setImageResource(category.icon)
        
    }

}