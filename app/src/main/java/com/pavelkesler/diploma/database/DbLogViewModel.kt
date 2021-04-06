package com.pavelkesler.diploma.database

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.pavelkesler.diploma.ProcessNumber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.concurrent.thread
import kotlin.random.Random

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
        if (coroutined) {
            for (i in 0..ProcessNumber) {
                val uid = (System.currentTimeMillis() % Int.MAX_VALUE + Random.nextInt(0, 10000000)).toString()
                println("Inserting dbLog $uid")
                // Generate ID in a simple way - from timestamp.
                val dbLogObj = DbLog(
                    uid, event, LocalDateTime.now().toString())
                GlobalScope.launch {
                    db.dbLogDao().insert(dbLogObj)
                    viewModelScope.launch {
                        logs = logs + listOf(dbLogObj)
                    }
                }
                if (i==ProcessNumber) loading = mutableListOf(false)
            }

        } else {
            for (i in 0..ProcessNumber) {
                val uid = (System.currentTimeMillis() % Int.MAX_VALUE + Random.nextInt(0, 10000000)).toString()
                println("Inserting dbLog $uid")

                val dbLogObj = DbLog(
                    uid, event, LocalDateTime.now().toString())
                thread(start = true) {
                    db.dbLogDao().insert(dbLogObj)
                    logs = logs + listOf(dbLogObj)
                }
                if (i==ProcessNumber) loading = mutableListOf(false)
            }
        }

    }

    fun removeAll() {
        println("Deleting all logs")
        logs = listOf()
        GlobalScope.launch { db.dbLogDao().delete() }
    }

}