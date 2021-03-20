package com.pavelkesler.diploma.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DbLogDao {
    @Query("SELECT * FROM primary_table")
    fun getAll(): List<DbLog>

    @Query("SELECT * FROM primary_table WHERE uid = :uid")
    fun getById(uid: Int): DbLog

    @Insert
    fun insertAll(vararg dbLogs: DbLog)

    @Insert
    suspend fun insert(dbLog: DbLog)

    @Query("DELETE FROM primary_table")
   suspend fun delete()
}

