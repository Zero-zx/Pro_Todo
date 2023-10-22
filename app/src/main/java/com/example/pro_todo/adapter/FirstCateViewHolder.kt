package com.example.pro_todo.adapter

import android.content.Context
import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.model.Category


class FirstCateViewHolder(private val binding: CateViewBinding,
                          private val items: List<Category>,
): RecyclerView.ViewHolder(binding.root) {


    fun bind(category: Category, context: Context, onClick: (Category) -> Unit ){
        binding.ivIc.setImageResource(category.icon)
        binding.tvTitle.text = category.title
        binding.clBackground.backgroundTintList = ColorStateList.valueOf(context.getColor(category.color))
        binding.clBackground.setOnClickListener{
            onClick(category)
        }
    }

}