package com.example.pro_todo.converter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Converter {

//    @TypeConverter
//    fun toLocalTime(time: String?): LocalTime? {
//        return if (time.isNullOrEmpty()) null
//        else LocalTime.parse(time)
//    }
//
//    @TypeConverter
//    fun fromLocalTime(time: LocalTime?): String? {
//        return DateTimeFormatter.ISO_LOCAL_TIME.format(time)
//    }

    @TypeConverter
    fun fromTimestamp(date: Long): Date{
        return Date(date)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long{
        return date.time
    }
}