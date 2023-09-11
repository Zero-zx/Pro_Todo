package com.example.pro_todo.View.DailyTaskScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pro_todo.Model.Task
import com.example.pro_todo.R
import com.example.pro_todo.ViewModel.TaskViewModel
import com.example.pro_todo.ViewModel.TaskViewModelFactory
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.UUID

class DailyTaskFragment : Fragment() {
    private lateinit var binding: FragmentDailyTaskBinding
    private lateinit var btnSaveTask: Button
    private lateinit var taskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyTaskBinding.inflate(inflater, container, false)
        initComponents()
        onClickListener()
        return binding.root
    }

    private fun onClickListener() {
        btnSaveTask.setOnClickListener{
            val task = Task(
                UUID.randomUUID().toString(),
                binding.tvDailyTask.text.toString(),
                false
            )

            taskViewModel.insert(task)
        }
    }

    private fun initComponents() {
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory()).get(TaskViewModel::class.java)
        btnSaveTask = binding.btnSaveTask

    }


}