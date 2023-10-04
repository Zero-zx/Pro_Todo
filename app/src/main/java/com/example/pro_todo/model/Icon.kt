package com.example.pro_todo.model

import com.example.pro_todo.R

object Icon {
    fun getIcons(): List<Int>{
        val iconsList = listOf<Int>(
            R.drawable.ic_study,
            R.drawable.ic_2,
            R.drawable.ic_3,
            R.drawable.ic_4,
            R.drawable.ic_5,
            R.drawable.ic_6,
            R.drawable.ic_7,
        )
        return iconsList
    }

}