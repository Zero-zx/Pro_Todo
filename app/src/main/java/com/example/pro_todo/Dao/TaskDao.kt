package com.example.pro_todo.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pro_todo.Model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

//    @Query("")
//    fun getTasks(): Flow<List<Task>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)
}