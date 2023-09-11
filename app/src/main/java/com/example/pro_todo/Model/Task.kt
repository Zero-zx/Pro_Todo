package com.example.pro_todo.Model

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val id: String,
    @ColumnInfo(name = "task_title")
    val title: String,
    val isDone: Boolean,

)