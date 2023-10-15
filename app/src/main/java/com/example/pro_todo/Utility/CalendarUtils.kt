package com.example.pro_todo.Utility

import android.util.Log
import java.time.LocalDate

object CalendarUtils {

    fun getDaysOfWeek(): ArrayList<LocalDate> {
        val daysOfWeek = ArrayList<LocalDate>()
        var today = LocalDate.now()

        today = today.minusDays((today.dayOfWeek.value-1).toLong());
        Log.d("TAG", "getDaysOfWeek: " + today.dayOfWeek.value)
        for (i in 0 until 7) {
            daysOfWeek.add(today)
            today = today.plusDays(1)
        }

        return daysOfWeek
    }
}