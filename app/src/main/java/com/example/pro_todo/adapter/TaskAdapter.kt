package com.example.pro_todo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.model.Task
import com.example.pro_todo.databinding.TaskViewBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter(
    private val context: Context,
    private val onClick: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: List<Task> = listOf()

    inner class TaskViewHolder(val binding: TaskViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskViewBinding.inflate(layoutInflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.apply {
            val task = taskList[position]
            tvTask.text = task.title
            if(task.icon != 0) ivIcon.setImageResource(task.icon)
            val dateFormat = SimpleDateFormat("HH:mm a dd/MM", Locale.getDefault())
            tvTime.text = dateFormat.format(task.date)
            dailyTaskList.setOnClickListener {
                onClick(task)
            }
//            Toast.makeText(context, task.date.toString(), Toast.LENGTH_SHORT).show()
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