package com.example.pro_todo.view.homeScreen

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CalendarView
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.Utility.displayText
import com.example.pro_todo.Utility.setTextColorRes
import com.example.pro_todo.adapter.CateAdapter
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.CalendarDayLayoutBinding
import com.example.pro_todo.databinding.FragmentHomeBinding
import com.example.pro_todo.model.Category
import com.example.pro_todo.model.Icon
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TodoRepository
import com.example.pro_todo.view.dailyTaskScreen.DailyTaskFragment
import com.example.pro_todo.viewModel.CateViewModel
import com.example.pro_todo.viewModel.CateViewModelFactory
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekCalendarView
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class HomeFragment : Fragment() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var cateViewModel: CateViewModel
    private lateinit var nav: BottomNavigationView
    private lateinit var rvCategory: RecyclerView
    private lateinit var rvAllTask: RecyclerView
    private lateinit var adapter: CateAdapter
    private lateinit var taskAdapter: TaskAdapter

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
        rvCategory = binding.rvCategory
        rvAllTask = binding.rvAllTask
        adapter = CateAdapter(requireContext(), CateAdapter.FIRST_VIEW, onItemCLick, onItemDelete, Icon.getIcons())
        taskAdapter = TaskAdapter(requireContext(), onCLick, onDelete)

        rvCategory.adapter = adapter
        rvAllTask.adapter = taskAdapter
        rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val repository = TodoRepository(TaskDatabase.getInstance(requireContext()).cateDao(), TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = CateViewModelFactory(repository)
        cateViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CateViewModel::class.java]
        cateViewModel.getAllCate().observe(viewLifecycleOwner, Observer { cate ->
            adapter.setCate(cate)
        })
        rvAllTask.layoutManager = LinearLayoutManager(requireContext())
        taskViewModel = ViewModelProvider(requireActivity(), TaskViewModelFactory(repository))[TaskViewModel::class.java]
        taskViewModel.getAllTask().observe(viewLifecycleOwner, Observer { task ->
            taskAdapter.setTasks(task)
        })

        nav = requireActivity().findViewById(R.id.btNavigation)
        nav.visibility = View.VISIBLE

       }

    private val onItemCLick: (Category) -> Unit={
        val dailyTaskFragment = TaskByCategoryFragment()
        parentFragmentManager.setFragmentResult("requestKey", Bundle().apply {
            putInt("cateId", it.categoryId)
        })

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, dailyTaskFragment)
            addToBackStack("")
            nav.visibility = View.GONE
            commit()
        }
    }

    private val onItemDelete: (Category) -> Unit={
        cateViewModel.deleteCate(it)
    }
    private val onCLick: (Task) -> Unit={

    }

    private val onDelete: (Task) -> Unit={
    }

}