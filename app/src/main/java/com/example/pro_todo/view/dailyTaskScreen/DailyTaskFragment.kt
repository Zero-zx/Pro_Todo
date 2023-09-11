package com.example.pro_todo.view.dailyTaskScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.viewModel.TaskViewModel
import java.util.UUID

class DailyTaskFragment : Fragment() {
    private lateinit var binding: FragmentDailyTaskBinding
    private lateinit var btnSaveTask: Button
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var rvDailyTask: RecyclerView
//    private val adapter = TaskAdapter()

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
        btnSaveTask.setOnClickListener{
            val task = Task(
                UUID.randomUUID().toString().toInt(),
                binding.tvDailyTask.text.toString(),
                false
            )

            taskViewModel.insertTask(task)
        }
    }

    private fun initComponents() {
        btnSaveTask = binding.btnSaveTask
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        rvDailyTask = binding.rvDailyTask
//        rvDailyTask.adapter = adapter
        rvDailyTask.layoutManager = LinearLayoutManager(requireContext())
        taskViewModel.getAllTask().observe(viewLifecycleOwner, Observer { task ->
//            adapter.setTasks(task)
        })
    }


}