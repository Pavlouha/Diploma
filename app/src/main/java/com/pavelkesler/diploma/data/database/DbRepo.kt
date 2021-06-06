package com.pavelkesler.diploma.data.database

import androidx.annotation.WorkerThread

class DbRepo(private val dbLogDao: DbLogDao) {

    val allData: List<DbLog> = dbLogDao.getAll()

    @WorkerThread
    fun insert(dbLog: DbLog) {
        print("Inserting dbLog ${dbLog.uid}")
        dbLogDao.insert(dbLog)
    }

    @WorkerThread
    fun insertAll(vararg dbLog: DbLog) {
        print("Inserting many dbLogs")
        dbLogDao.insertAll(*dbLog)
    }


    fun byId(uid: Int): DbLog = dbLogDao.getById(uid)

    @WorkerThread
    fun deleteAll() {
        print("Deleting all logs")
        dbLogDao.delete()
    }

}