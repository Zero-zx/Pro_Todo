package com.example.pro_todo.repository

import androidx.lifecycle.LiveData
import com.example.pro_todo.database.CategoryDao
import com.example.pro_todo.model.Category

class CateRepository(private val categoryDao: CategoryDao)  {
    suspend fun insertCate(category: Category) = categoryDao.insertCate(category)
    suspend fun updateCate(category: Category) = categoryDao.updateCate(category)
    suspend fun deleteCate(category: Category) = categoryDao.deleteCate(category)
    fun getAllCate(): LiveData<List<Category>> = categoryDao.getAllCate()
}