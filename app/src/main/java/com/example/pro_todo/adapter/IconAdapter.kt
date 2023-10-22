package com.example.pro_todo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.SmallTagViewBinding
import com.example.pro_todo.databinding.TaskViewBinding
import com.example.pro_todo.model.Icon
import com.example.pro_todo.model.Task
import java.text.SimpleDateFormat
import java.util.Locale

class IconAdapter(
    private val context: Context,
    private val onClick: (Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {
    private var iconList: List<Int> = Icon.getIcons()

    inner class IconViewHolder(val binding: SmallTagViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SmallTagViewBinding.inflate(layoutInflater, parent, false)
        return IconViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.binding.apply {
            ivIcon.setImageResource(iconList[position])
            ivIcon.setOnClickListener {
                onClick(iconList[position]) }
        }
    }

    override fun getItemCount(): Int {
        return iconList.size
    }

    fun updateIcon(position: Int){
        onClick(iconList[position])
    }
}