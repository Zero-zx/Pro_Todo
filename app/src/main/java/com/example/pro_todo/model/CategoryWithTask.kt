package com.example.pro_todo.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithTask (
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryCreateId"
    )
    val tasklist: List<Task>
)