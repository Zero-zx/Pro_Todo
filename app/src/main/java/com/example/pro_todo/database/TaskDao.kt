package com.example.pro_todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pro_todo.model.Task
import java.util.Date

@Dao
interface TaskDao {

//    @Query("")
//    fun getTasks(): Flow<List<Task>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)

    @Query("select * from task_table")
    fun getAllTask(): LiveData<List<Task>>
    @Query("select * from task_table where date =:date")
    fun getTaskByDate(date: Date): LiveData<List<Task>>
}