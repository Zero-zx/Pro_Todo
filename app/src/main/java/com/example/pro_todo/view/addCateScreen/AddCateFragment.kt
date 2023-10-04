package com.example.pro_todo.view.addCateScreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.adapter.ColorAdapter
import com.example.pro_todo.adapter.IconAdapter
import com.example.pro_todo.callback.DialogFragmentCallback
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentAddCateBinding
import com.example.pro_todo.databinding.FragmentAddTaskBinding
import com.example.pro_todo.model.Cate
import com.example.pro_todo.model.Icon
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.view.addTaskScreen.AddTagFragment
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import java.util.Date

class AddCateFragment: Fragment() {
    private lateinit var binding: FragmentAddCateBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var rvCategory: RecyclerView
    private lateinit var rvColor: RecyclerView
    private lateinit var btnCreateCate: Button
    private lateinit var btnCancel: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCateBinding.inflate(inflater, container, false)
        initComponent()
        onClickListener()
        return binding.root
    }

    private fun initComponent() {
        rvCategory = binding.rvCategory
        rvColor = binding.rvColor
        btnCreateCate = binding.btnCreateCate
        btnCancel = binding.btnCancel
        val repository = TaskRepository(TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]

        val iconAdapter = IconAdapter(requireContext(), onItemCLick, onItemDelete)
        rvCategory.adapter = iconAdapter

        val colorAdapter = ColorAdapter(requireContext(), onItemCLick, onItemDelete)
        rvColor.adapter = colorAdapter
    }

    private fun onClickListener() {
        btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnCreateCate.setOnClickListener {

        }
    }
    private val onItemCLick: (Icon) -> Unit={


    }

    private val onItemDelete: (Icon) -> Unit={
    }

}