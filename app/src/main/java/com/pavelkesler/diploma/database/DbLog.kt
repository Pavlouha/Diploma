package com.pavelkesler.diploma.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class DbLog(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "db_event") val dbEvent: String,
    @ColumnInfo(name = "date_time") val dateAndTime: String
)