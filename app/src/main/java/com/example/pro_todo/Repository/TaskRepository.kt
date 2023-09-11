package com.example.pro_todo.Repository

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.example.pro_todo.Dao.TaskDao
import com.example.pro_todo.Database.TaskDatabase
import com.example.pro_todo.Model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
//    val allTasks: Flow<List<Task>> = taskDao.getTasks()
    suspend fun insert(task: Task){
        taskDao.insert(task)
    }
}