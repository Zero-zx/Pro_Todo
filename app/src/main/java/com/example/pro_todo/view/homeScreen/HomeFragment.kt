package com.example.pro_todo.view.homeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.adapter.CateAdapter
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.CateDatabase
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.databinding.FragmentHomeBinding
import com.example.pro_todo.model.Cate
import com.example.pro_todo.model.Icon
import com.example.pro_todo.repository.CateRepository
import com.example.pro_todo.viewModel.CateViewModel
import com.example.pro_todo.viewModel.CateViewModelFactory
import com.example.pro_todo.viewModel.TaskViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var cateViewModel: CateViewModel

    private lateinit var rvDailyTask: RecyclerView
    private lateinit var adapter: CateAdapter

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
        rvDailyTask = binding.rvDailyTask
        adapter = CateAdapter(requireContext(), CateAdapter.FIRST_VIEW, onItemCLick, onItemDelete, Icon.getIcons())
        rvDailyTask.adapter = adapter
        rvDailyTask.layoutManager = LinearLayoutManager(requireContext())
        val repository = CateRepository(CateDatabase.getInstance(requireContext()).cateDao())
        val viewModelFactory = CateViewModelFactory(repository)
        cateViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CateViewModel::class.java]
        cateViewModel.getAllCate().observe(viewLifecycleOwner, Observer { cate ->
            adapter.setCate(cate)
        })
    }

    private fun initComponents() {
    }

    private val onItemCLick: (Cate) -> Unit={
    }

    private val onItemDelete: (Cate) -> Unit={
        cateViewModel.deleteCate(it)
    }
}