package com.example.pro_todo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.Model.Task
import com.example.pro_todo.R
import com.example.pro_todo.ViewModel.TaskViewModel
import com.example.pro_todo.databinding.TaskViewBinding

class TaskListAdapter() : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    private val taskList: ArrayList<Task> = arrayListOf<Task>()

    inner class TaskViewHolder(val binding: TaskViewBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskViewBinding.inflate(layoutInflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.bin
    }
}



}