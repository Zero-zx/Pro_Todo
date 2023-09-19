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
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.view.dialog.AddTaskFragment
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory

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
            val fragmentManager = childFragmentManager
            val dialog = AddTaskFragment()
            dialog.show(fragmentManager, "my_dialog")
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

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvDailyTask)

    }

    val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            if (direction == ItemTouchHelper.LEFT) {
                adapter.deleteTask(position)
               // adapter.notifyItemChanged(position)
                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
            } else if (direction == ItemTouchHelper.RIGHT) {
                adapter.notifyItemChanged(position)
                Toast.makeText(requireContext(), "Swipe right", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val onItemCLick: (Task) -> Unit={
    }

    private val onItemDelete: (Task) -> Unit={
        taskViewModel.deleteTask(it)
    }
}