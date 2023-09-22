package com.example.pro_todo.repository

import androidx.lifecycle.LiveData
import com.example.pro_todo.database.CateDao
import com.example.pro_todo.database.TaskDao
import com.example.pro_todo.model.Cate
import com.example.pro_todo.model.Task

class CateRepository(private val cateDao: CateDao)  {
    suspend fun insertCate(cate: Cate) = cateDao.insertTask(cate)
    suspend fun updateCate(cate: Cate) = cateDao.updateTask(cate)
    suspend fun deleteCate(cate: Cate) = cateDao.deleteTask(cate)
    fun getAllCate(): LiveData<List<Cate>> = cateDao.getAllTask()
}