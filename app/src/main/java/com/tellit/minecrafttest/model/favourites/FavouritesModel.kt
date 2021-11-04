package com.tellit.minecrafttest.model.favourites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_table")

data class FavouritesModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "5mkbgj_pw")
    val mkbgjpw: String?,
    @ColumnInfo(name = "5mkbgjt3")
    val mkbgjt3: String?,
    @ColumnInfo(name = "5mkbgj_ieq")
    val mkbgjieq: String?,
    @ColumnInfo(name = "5mkbgji1")
    val mkbgji1: String?,
    @ColumnInfo(name = "5mkbgjd4")
    val mkbgjd4: String?,
    @ColumnInfo(name = "5mkbgjf2")
    val mkbgjf2: String?,
    @ColumnInfo (name = "Status")
    val status : Boolean = false
)

