package com.example.pro_todo.model

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val isDone: Boolean,

)