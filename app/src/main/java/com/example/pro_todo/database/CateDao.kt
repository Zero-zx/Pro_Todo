package com.example.pro_todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pro_todo.model.Cate
import com.example.pro_todo.model.Task
@Dao
interface CateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCate(cate: Cate)
    @Update
    suspend fun updateCate(cate: Cate)
    @Delete
    suspend fun deleteCate(cate: Cate)

    @Query("select * from cate_table")
    fun getAllCate(): LiveData<List<Cate>>
}