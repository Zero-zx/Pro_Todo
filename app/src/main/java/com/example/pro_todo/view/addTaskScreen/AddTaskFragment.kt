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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pro_todo.callback.DialogFragmentCallback
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentAddTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TodoRepository
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

    private var iconPath = 0
    private var cateName = ""

    private var editTime = false
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
        val repository = TodoRepository(TaskDatabase.getInstance(requireContext()).cateDao(), TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]
        val task = requireArguments().getSerializable("task") as Task?
        if(task != null){
            etTitle.setText(task.title)
            etDes.setText(task.des)
        }
    }

    private fun onClickListener(){
        pickDate()
        btnSave.setOnClickListener{
            val task = requireArguments().getSerializable("task") as Task?
            if(task != null){

                task.title = etTitle.text.toString()
                task.des = etDes.text.toString()
                if(editTime) task.date = Date(savedYear, savedMonth, savedDay, savedHour, savedMinute)
                task.categoryCreateId = cateId
                task.type = cateName
                task.icon = iconPath
                taskViewModel.updateTask(task)
                dismiss()
            }else{
                val newTask = Task( etTitle.text.toString(), etDes.text.toString(), Date(savedYear, savedMonth, savedDay, savedHour, savedMinute), false, cateName, cateId, iconPath)
                taskViewModel.insertTask(newTask)
                dismiss()
            }
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
            editTime = true
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

    override fun onDataReceived(id: Int, iconRes: Int, name: String) {
        cateId = id
        iconPath = iconRes
        cateName = name
    }
}