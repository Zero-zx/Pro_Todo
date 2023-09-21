package com.example.pro_todo.view.homeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.databinding.FragmentHomeBinding
import com.example.pro_todo.viewModel.TaskViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskViewModel: TaskViewModel

    private lateinit var rvDailyTask: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initComponents()
        onClickListener()
        return binding.root
    }

    private fun onClickListener() {
    }

    private fun initComponents() {
    }
}