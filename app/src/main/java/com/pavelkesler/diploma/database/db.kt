package com.pavelkesler.diploma.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbLogDao(): DbLogDao
}