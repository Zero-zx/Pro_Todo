package com.example.pro_todo.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "task_table")
data class Task(
    var title: String,
    var des: String,
    var date: Date,
    val isDone: Boolean,
    var type: String,
    var categoryCreateId: Int,
    var icon: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable
