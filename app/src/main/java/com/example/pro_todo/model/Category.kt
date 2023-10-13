package com.example.pro_todo.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cate_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val haveDone: Int,
    val size: Int,
    val icon: Int,
    val color: Int
){
    constructor(title: String, haveDone: Int, size: Int, icon: Int, color: Int): this(0, title, haveDone, size, icon, color)
}
