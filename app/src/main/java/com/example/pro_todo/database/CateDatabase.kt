package com.example.pro_todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pro_todo.converter.Converter
import com.example.pro_todo.model.Cate

@Database(
    entities = [Cate::class],
    version = 1,
    exportSchema = false
)
abstract class CateDatabase : RoomDatabase() {
    abstract fun cateDao(): CateDao

    companion object{
        private var INSTANCE : CateDatabase? = null
        fun getInstance(context: Context) : CateDatabase{
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    CateDatabase::class.java,
                    "cate_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}