package com.example.pro_todo.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.databinding.ColorViewBinding
import com.example.pro_todo.model.Color
import com.example.pro_todo.model.Icon


class ColorAdapter(
    private val context: Context,
    private val onClick: (Icon) -> Unit,
    private val onDelete: (Icon) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private var colorList: List<Int> = Color.getColors()

    inner class ColorViewHolder(val binding: ColorViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ColorViewBinding.inflate(layoutInflater, parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.binding.apply {
//            ivColor.setBackgroundColor( ContextCompat.getColor(context,  colorList[position]))
            ivColor.setBackgroundColor(colorList[position])
        }
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

}