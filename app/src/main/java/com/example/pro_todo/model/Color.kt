package com.example.pro_todo.model

import com.example.pro_todo.R

object Color {
    fun getColors(): List<Int>{
        val colorList = listOf<Int>(
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8
        )
        return colorList
    }

}