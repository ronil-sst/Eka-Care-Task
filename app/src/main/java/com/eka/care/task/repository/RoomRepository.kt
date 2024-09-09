package com.eka.care.task.repository

import com.eka.care.task.room.dao.EntryDao
import com.eka.care.task.room.models.EntryModel
import com.eka.care.task.utils.Resource
import com.eka.care.task.utils.StatusResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RoomRepository @Inject constructor(private val taskDao: EntryDao) {

    fun getAllEntries(callback: (Resource<Flow<List<EntryModel>>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                callback(Resource.Loading())
                delay(500)
                val result = taskDao.getAllEntries()
                withContext(Dispatchers.Main) {
                    callback(Resource.Success("Data Fetched Successfully", result))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(Resource.Error(e.message.toString()))
                }
            }
        }
    }


    fun insertTask(task: EntryModel, callback: (Resource<Flow<EntryModel>>) -> Unit) {
        try {
            callback(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                delay(2000)
                val result = taskDao.insertTask(task)
                withContext(Dispatchers.Main) {
                    if (result.toInt() != -1) {

                        callback(Resource.Success("Inserted EntryModel Successfully"))
                    } else {
                        callback(Resource.Error("Some Error Occur"))

                    }
                }
            }
        } catch (e: Exception) {
            callback(Resource.Error(e.message.toString()))
        }
    }


    fun deleteTask(task: EntryModel) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val result = taskDao.deleteTask(task)
                handleResult(result, "Deleted EntryModel Successfully", StatusResult.Deleted)
            }
        } catch (e: Exception) {
        }
    }


    fun updateTask(task: EntryModel) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val result = taskDao.updateTask(task)
                handleResult(result, "Updated EntryModel Successfully", StatusResult.Updated)

            }
        } catch (e: Exception) {
        }
    }


    private fun handleResult(result: Int, message: String, statusResult: StatusResult) {
        if (result != -1) {
        } else {
        }
    }


}