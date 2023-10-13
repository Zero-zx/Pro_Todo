package com.example.pro_todo.view.addTaskScreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProvider
import com.example.pro_todo.R
import com.example.pro_todo.callback.DialogFragmentCallback
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentAddTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import java.util.Date

class AddTaskFragment : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, DialogFragmentCallback {
    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var etTitle: EditText
    private lateinit var etDes: EditText
    private lateinit var btnSetTime: ImageButton
    private lateinit var btnSetTag: ImageButton
    private lateinit var btnSave: ImageButton

    private var cateId = 0;

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0
    private var savedHour = 0
    private var savedMinute = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        initComponent()
        onClickListener()
        return binding.root
    }

    private fun initComponent() {
        etTitle = binding.etTitle
        etDes = binding.etDescription
        btnSetTime = binding.btnSetTime
        btnSetTag = binding.btnSetTag
        btnSave = binding.btnSave

        val repository = TaskRepository(TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]
    }

    private fun onClickListener(){
        pickDate()
        btnSave.setOnClickListener{
            val task = Task( etTitle.text.toString(), etDes.text.toString(), Date(savedYear, savedMonth, savedDay, savedHour, savedMinute), false, "Study", cateId, R.drawable.ic_study)
            taskViewModel.insertTask(task)
            dismiss()
        }

        btnSetTag.setOnClickListener{
            val fragmentManager = childFragmentManager
            val dialog = AddCategoryFragment()
            dialog.setCallback(this)
            dialog.show(fragmentManager, "my_dialog")
        }

    }

    private fun pickDate() {
        btnSetTime.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(requireContext(), this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
    }

    override fun onDataReceived(id: Int) {
        cateId = id
    }
}