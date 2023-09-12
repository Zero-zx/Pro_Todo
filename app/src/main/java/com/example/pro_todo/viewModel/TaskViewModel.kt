package com.example.pro_todo.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pro_todo.database.TaskDao
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
): ViewModel() {

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }

    fun getAllTask():LiveData<List<Task>> = repository.getAllTask()
}
class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

