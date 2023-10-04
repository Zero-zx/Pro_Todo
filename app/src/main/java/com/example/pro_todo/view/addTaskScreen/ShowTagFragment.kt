package com.example.pro_todo.view.dailyTaskScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.adapter.CateAdapter
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.databinding.FragmentShowTagBinding
import com.example.pro_todo.model.Cate
import com.example.pro_todo.model.Icon
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.view.addTaskScreen.AddTaskFragment
import com.example.pro_todo.viewModel.CateViewModel
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory

class ShowTagFragment : Fragment() {
    private lateinit var binding: FragmentShowTagBinding
    private lateinit var cateViewModel: CateViewModel
    private lateinit var rvShowTag: RecyclerView
    private lateinit var adapter: CateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowTagBinding.inflate(inflater, container, false)
        initComponents()
        onClickListener()
        return binding.root
    }

    private fun onClickListener() {

    }

    private fun initComponents() {
//        rvDailyTask = binding.rvDailyTask
        adapter = CateAdapter(requireContext(), CateAdapter.SECOND_VIEW, onItemCLick, onItemDelete, Icon.getIcons())
        val repository = TaskRepository(TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        cateViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CateViewModel::class.java]
//        rvDailyTask.adapter = adapter
//        rvDailyTask.layoutManager = LinearLayoutManager(requireContext())

        cateViewModel.getAllCate().observe(viewLifecycleOwner, Observer { cate ->
            adapter.setCate(cate)
        })


    }



    private val onItemCLick: (Cate) -> Unit={
    }

    private val onItemDelete: (Cate) -> Unit={
        cateViewModel.deleteCate(it)
    }
}