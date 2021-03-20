package com.pavelkesler.diploma.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DbRepo(private val dbLogDao: DbLogDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allData: List<DbLog> = dbLogDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(dbLog: DbLog) {
        print("Inserting dbLog ${dbLog.uid}")
        dbLogDao.insert(dbLog)
    }

    @WorkerThread
    suspend fun insertAll(vararg dbLog: DbLog) {
        print("Inserting many dbLogs")
        dbLogDao.insertAll(*dbLog)
    }


    fun byId(uid: Int): DbLog = dbLogDao.getById(uid)

    @WorkerThread
    suspend fun deleteAll() {
        print("Deleting all logs...")
        dbLogDao.delete()
    }

}