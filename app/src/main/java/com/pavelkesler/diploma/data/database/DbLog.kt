package com.pavelkesler.diploma.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbLog(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "db_event") val dbEvent: String,
    @ColumnInfo(name = "date_time") val dateAndTime: String
)