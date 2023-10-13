package com.example.pro_todo.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
import java.sql.Time
import java.time.LocalTime
import java.util.Date

@Entity(tableName = "task_table")
data class Task (
    val title: String,
    val des: String,
    val date: Date,
    val isDone: Boolean,
    val type: String,
    val categoryId: Int,
    val icon: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

