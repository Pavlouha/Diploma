package com.pavelkesler.diploma.database

import androidx.room.*

@Dao
interface DbLogDao {
    @Query("SELECT * FROM primary_table")
    fun getAll(): List<DbLog>

    @Query("SELECT * FROM primary_table WHERE uid IN (:dbLogIds)")
    fun loadAllByIds(userIds: IntArray): List<DbLog>

    @Query("SELECT * FROM primary_table WHERE date_time")
    fun findByName(first: String, last: String): DbLog

    @Insert
    fun insertAll(vararg dbLogs: DbLog)

    @Query("DELETE FROM primary_table")
    fun delete(dbLog: DbLog)
}

