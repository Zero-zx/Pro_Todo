package com.example.pro_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.ActivityMain2Binding
import com.example.pro_todo.databinding.ActivityMainBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory

class MainActivity2 : AppCompatActivity() {
    private val database by lazy { TaskDatabase.getInstance(this) }
    private val taskViewModel: TaskViewModel by lazy {
        ViewModelProvider(this, TaskViewModelFactory(TaskRepository(database.taskDao())))[TaskViewModel::class.java]
    }

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            val task = Task(0, binding.etTitle.text.toString(), false)
            taskViewModel.insertTask(task)
            finish()
        }
    }
}