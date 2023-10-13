package com.example.pro_todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pro_todo.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCate(category: Category)
    @Update
    suspend fun updateCate(category: Category)
    @Delete
    suspend fun deleteCate(category: Category)

    @Query("select * from cate_table")
    fun getAllCate(): LiveData<List<Category>>

    @Query("SELECT * FROM cate_table WHERE id = :id")
    fun getUserById(id: Long): Category
}