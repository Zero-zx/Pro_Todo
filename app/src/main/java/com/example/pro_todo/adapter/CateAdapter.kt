package com.example.pro_todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.databinding.TaskViewBinding
import com.example.pro_todo.viewModel.CateViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class CateAdapter(
    private val context: Context,
    private val onClick: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : RecyclerView.Adapter<CateAdapter.CateViewHolder>() {
    private var taskList: List<Task> = listOf()

    inner class CateViewHolder(val binding: CateViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CateViewBinding.inflate(layoutInflater, parent, false)
        return CateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CateViewHolder, position: Int) {
        holder.binding.apply {
            val task = taskList[position]

        }
    }



    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setTasks(tasks: List<Task>){
        this.taskList = tasks
        notifyDataSetChanged()
    }

    fun deleteTask(position: Int){
        onDelete(taskList[position])
        notifyDataSetChanged()
    }
}