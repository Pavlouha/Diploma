package com.pavelkesler.diploma.database

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.concurrent.thread

class DbLogViewModel(application: Application) : AndroidViewModel(application){

private val db = Room.databaseBuilder(
    application.applicationContext,
    AppDatabase::class.java,
    "app_database"
).build()

// Load initial data from Room asynchronously.
init {
    GlobalScope.launch {
        val items = db.dbLogDao().getAll()
        viewModelScope.launch {
            logs = items
            loading = mutableListOf(false)
        }
    }
}

    var logs by mutableStateOf(listOf<DbLog>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    fun addDbLog(event: String, coroutined: Boolean) {
        loading = mutableListOf(true)
        val uid = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
        println("Inserting dbLog $uid")
        // Generate ID in a simple way - from timestamp.
        val dbLogObj = DbLog(
            uid, event, LocalDateTime.now().toString())

        logs = logs + listOf(dbLogObj)
        loading = mutableListOf(false)
        if (coroutined) {
            GlobalScope.launch {
                db.dbLogDao().insert(dbLogObj)
            }
        } else {
            thread(start = true) {
                    runBlocking {
                        db.dbLogDao().insert(dbLogObj)
                    }
            }
        }

    }



    fun removeAll() {
        println("Deleting all logs")
        logs = listOf()
        GlobalScope.launch { db.dbLogDao().delete() }
    }

}