package com.example.pro_todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "cate_table")
data class Cate(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val size: Int,
    val icon: Int
){
    constructor(title: String, size: Int, icon: Int): this(0, title, size, icon)
}
