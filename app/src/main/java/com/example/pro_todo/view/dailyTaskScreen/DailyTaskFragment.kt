package com.example.pro_todo.view.dailyTaskScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.MainActivity
import com.example.pro_todo.MainActivity2
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import java.util.UUID

class DailyTaskFragment : Fragment() {
    private lateinit var binding: FragmentDailyTaskBinding
    private lateinit var taskViewModel: TaskViewModel

    private lateinit var rvDailyTask: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyTaskBinding.inflate(inflater, container, false)
        initComponents()
        onClickListener()
        return binding.root
    }

    private fun onClickListener() {
        binding.fabAddTask.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity2::class.java)
            Log.d("Check", "3")
            startActivity(intent)
        }
    }

    private fun initComponents() {
        rvDailyTask = binding.rvDailyTask
        adapter = TaskAdapter(requireContext(), onItemCLick, onItemDelete)
        val repository = TaskRepository(TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]
        rvDailyTask.adapter = adapter
        rvDailyTask.layoutManager = LinearLayoutManager(requireContext())
        taskViewModel.getAllTask().observe(viewLifecycleOwner, Observer { task ->
            adapter.setTasks(task)
        })
    }

    private val onItemCLick: (Task) -> Unit={

    }

    private val onItemDelete: (Task) -> Unit={

    }}