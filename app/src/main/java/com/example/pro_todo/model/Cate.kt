package com.example.pro_todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "cate_table")
data class Cate(
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
