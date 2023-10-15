package com.example.pro_todo.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cate_table")
data class Category(
    val title: String,
    val haveDone: Int,
    val size: Int,
    val icon: Int,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val categoryId: Int = 0
)


