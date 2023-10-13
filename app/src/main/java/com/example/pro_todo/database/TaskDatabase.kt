package com.example.pro_todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pro_todo.converter.Converter
import com.example.pro_todo.model.Category
import com.example.pro_todo.model.Task

@Database(
    entities = [Task::class, Category::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun cateDao(): CategoryDao

    companion object{
        private var INSTANCE : TaskDatabase? = null
        fun getInstance(context: Context) : TaskDatabase{
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }

}