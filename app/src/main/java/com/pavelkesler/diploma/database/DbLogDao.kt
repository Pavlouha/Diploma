package com.pavelkesler.diploma.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DbLogDao {
    @Query("SELECT * FROM dblog")
    fun getAll(): List<DbLog>

    @Query("SELECT * FROM dblog WHERE uid = :uid")
    fun getById(uid: Int): DbLog

    @Insert
    fun insertAll(vararg dbLogs: DbLog)

    @Insert
    fun insert(dbLog: DbLog)

    @Query("DELETE FROM dblog")
    fun delete()
}