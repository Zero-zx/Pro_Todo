package com.example.pro_todo.view.homeScreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentTaskByCategoryBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TodoRepository
import com.example.pro_todo.view.addTaskScreen.AddTaskFragment
import com.example.pro_todo.viewModel.CateViewModelFactory
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class TaskByCategoryFragment : DialogFragment() {
    private lateinit var binding: FragmentTaskByCategoryBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var rvAddTag: RecyclerView
    private lateinit var adapter: TaskAdapter
    private lateinit var nav: BottomNavigationView
    private var filterCategoryId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskByCategoryBinding.inflate(inflater, container, false)
        initComponent()
        onClickListener()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            filterCategoryId = bundle.getInt("cateId")
            Toast.makeText(requireContext(), filterCategoryId.toString(), Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun initComponent() {
        adapter = TaskAdapter(requireContext(), onItemCLick, onItemDelete)
        rvAddTag = binding.rvAddTask
        rvAddTag.adapter = adapter
        nav = requireActivity().findViewById(R.id.btNavigation)
        nav.visibility = View.VISIBLE
        val repository = TodoRepository(TaskDatabase.getInstance(requireContext()).cateDao(), TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]


        taskViewModel.getAllTask().observe(viewLifecycleOwner, Observer { tasks ->
            tasks.let {
                val filterdTask = tasks.filter { task ->
                    task.categoryCreateId == filterCategoryId
                }
                adapter.setTasks(filterdTask)
            }
        })
    }

    private fun onClickListener() {
    }

    private val onItemCLick: (Task) -> Unit={
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.arguments = Bundle().apply {
            putSerializable("task", it)
        }
        addTaskFragment.show(childFragmentManager, "my_dialog")
        taskViewModel.updateTask(it)
    }

    private val onItemDelete: (Task) -> Unit={
        taskViewModel.deleteTask(it)
    }

}