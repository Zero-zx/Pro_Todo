package com.example.pro_todo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.pro_todo.database.TaskDao
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.model.Task

class TaskRepository(app: Application) {
    private val taskDao: TaskDao
    init {
        val taskDatabase: TaskDatabase = TaskDatabase.getInstance(app)
        taskDao = taskDatabase.taskDao()
    }

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    fun getAllTask(): LiveData<List<Task>> = taskDao.getAllTask()
}