package com.example.pro_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.databinding.ActivityMainBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private val taskViewModel: TaskViewModel by lazy {
        ViewModelProvider(this, TaskViewModel.TaskViewModelFactory(this.application)).get(TaskViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initComponent()
        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.btnSaveTask.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun initComponent() {
        val adapter: TaskAdapter = TaskAdapter(this@MainActivity, onItemCLick, onItemDelete)
        binding.rvDailyTask.layoutManager = LinearLayoutManager(this)
        binding.rvDailyTask.adapter = adapter
        taskViewModel.getAllTask().observe(this, Observer {
            adapter.setTasks(it)
        })
    }

    private val onItemCLick: (Task) -> Unit={

    }

    private val onItemDelete: (Task) -> Unit={

    }

}