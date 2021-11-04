package com.tellit.minecrafttest.room

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Aslbek on 03.11.2021.
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
//    @TypeConverter
//    fun fromTimestamp(value: Long?):
//            java.sql.Date {
//        return java.sql.Date(value ?: 0)
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: java.sql.Date?)
//            : Long {
//        return date?.time ?: 0
//    }
}