package com.example.pro_todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.model.Task
import com.example.pro_todo.databinding.TaskViewBinding

class TaskAdapter(
    private val context: Context,
    private val onClick: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: List<Task> = listOf()

    inner class TaskViewHolder(val binding: TaskViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskViewBinding.inflate(layoutInflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.apply {
            tvTask.text = taskList[position].title
            //tvTime.text = taskList[position].
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setTasks(tasks: List<Task>){
        this.taskList = tasks
        notifyDataSetChanged()
    }
}