package com.example.pro_todo.adapter

import android.content.Context
import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.TagViewBinding
import com.example.pro_todo.model.Category

class SecondCateViewHolder(val binding: TagViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, onClick: (Category) -> Unit, context: Context){
        binding.ivIcon.setImageResource(category.icon)
        binding.tvTitle.text = category.title
        binding.flBackground.backgroundTintList = ColorStateList.valueOf(context.getColor(category.color))
        binding.tagView.setOnClickListener{
            onClick(category)

        }
    }
}