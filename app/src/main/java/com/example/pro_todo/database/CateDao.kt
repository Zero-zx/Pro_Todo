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
    suspend fun insertTask(cate: Cate)
    @Update
    suspend fun updateTask(cate: Cate)
    @Delete
    suspend fun deleteTask(cate: Cate)

    @Query("select * from cate_table")
    fun getAllTask(): LiveData<List<Cate>>
}