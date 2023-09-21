package com.example.pro_todo.view.addTaskScreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TimePicker
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.pro_todo.R
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentAddTagBinding
import com.example.pro_todo.databinding.FragmentAddTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import java.util.Date

class AddTagFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTagBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTagBinding.inflate(inflater, container, false)
        initComponent()
        onClickListener()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    private fun initComponent() {


        val repository = TaskRepository(TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]
    }

    private fun onClickListener(){

    }

}