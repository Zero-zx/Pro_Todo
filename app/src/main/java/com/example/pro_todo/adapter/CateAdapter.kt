package com.example.pro_todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.databinding.TaskViewBinding
import com.example.pro_todo.model.Cate
import com.example.pro_todo.viewModel.CateViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class CateAdapter(
    private val context: Context,
    private val onClick: (Cate) -> Unit,
    private val onDelete: (Cate) -> Unit
) : RecyclerView.Adapter<CateAdapter.CateViewHolder>() {
    private var cateList: List<Cate> = listOf()

    inner class CateViewHolder(val binding: CateViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CateViewBinding.inflate(layoutInflater, parent, false)
        return CateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CateViewHolder, position: Int) {
        holder.binding.apply {
            val cate = cateList[position]

        }
    }



    override fun getItemCount(): Int {
        return cateList.size
    }

    fun setCate(cates: List<Cate>){
        this.cateList = cates
        notifyDataSetChanged()
    }

    fun deleteCate(position: Int){
        onDelete(cateList[position])
        notifyDataSetChanged()
    }
}