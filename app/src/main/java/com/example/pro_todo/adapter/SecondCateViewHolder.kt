package com.example.pro_todo.adapter

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.TagViewBinding
import com.example.pro_todo.model.Cate

class SecondCateViewHolder(private val binding: TagViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(cate: Cate, onClick: (Cate) -> Unit){
        binding.ivIcon.setImageResource(cate.icon)
        binding.tagView.setOnClickListener{
            onClick(cate)

        }
    }
}