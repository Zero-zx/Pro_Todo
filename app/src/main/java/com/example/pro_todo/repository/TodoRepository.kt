package com.example.pro_todo.repository

import androidx.lifecycle.LiveData
import com.example.pro_todo.database.CategoryDao
import com.example.pro_todo.database.TaskDao
import com.example.pro_todo.model.Category
import com.example.pro_todo.model.CategoryWithTask
import com.example.pro_todo.model.Task
import java.util.Date

class TodoRepository(private val categoryDao: CategoryDao, private val taskDao: TaskDao)  {
    suspend fun insertCate(category: Category) = categoryDao.insertCate(category)
    suspend fun updateCate(category: Category) = categoryDao.updateCate(category)
    suspend fun deleteCate(category: Category) = categoryDao.deleteCate(category)
    fun getAllCate(): LiveData<List<Category>> = categoryDao.getAllCate()
    fun getTasksOfCategory(categoryId: Int): LiveData<List<CategoryWithTask>> = categoryDao.getCategoryWithTask(categoryId)

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    fun getAllTask(): LiveData<List<Task>> = taskDao.getAllTask()
    fun getTaskByDate(date: Date): LiveData<List<Task>> = taskDao.getTaskByDate(date)
}