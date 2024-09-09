package com.eka.care.task.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eka.care.task.room.dao.EntryDao
import com.eka.care.task.room.models.EntryModel

import com.eka.care.task.utils.Resource
import com.eka.care.task.utils.StatusResult

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class RoomRepository @Inject constructor(private val taskDao: EntryDao) {
    private val pageSize = 10
    private val _totalPages = MutableStateFlow<Resource<Int>>(Resource.Loading())
    val totalPages: StateFlow<Resource<Int>>
        get() = _totalPages


    private val _taskStateFlow =
        MutableStateFlow<Resource<Flow<List<EntryModel>>>>(Resource.Loading())
    val taskStateFlow: StateFlow<Resource<Flow<List<EntryModel>>>>
        get() = _taskStateFlow

    private val _statusLiveData = MutableLiveData<Resource<StatusResult>>()
    val statusLiveData: LiveData<Resource<StatusResult>>
        get() = _statusLiveData


    fun getAllEntries(callback: (Resource<Flow<List<EntryModel>>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                callback(Resource.Loading())
                delay(500)
                val result = taskDao.getAllEntries()
                callback(Resource.Success("Data Fetched Successfully", result))

                _taskStateFlow.emit(Resource.Success("Data Fetched Successfully", result))
            } catch (e: Exception) {
                callback(Resource.Error(e.message.toString()))
            }
        }
    }


    fun insertTask(task: EntryModel, callback: (Resource<Flow<EntryModel>>) -> Unit) {
        try {
            callback(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = taskDao.insertTask(task)
                if (result.toInt() != -1) {
                    callback(Resource.Success("Inserted EntryModel Successfully"))
                } else {
                    callback(Resource.Error("Some Error Occur"))

                }
            }
        } catch (e: Exception) {
            callback(Resource.Error(e.message.toString()))
        }
    }


    fun deleteTask(task: EntryModel) {
        try {
            _statusLiveData.postValue(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = taskDao.deleteTask(task)
                handleResult(result, "Deleted EntryModel Successfully", StatusResult.Deleted)
            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }


    fun updateTask(task: EntryModel) {
        try {
            _statusLiveData.postValue(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = taskDao.updateTask(task)
                handleResult(result, "Updated EntryModel Successfully", StatusResult.Updated)

            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }


    private fun handleResult(result: Int, message: String, statusResult: StatusResult) {
        if (result != -1) {
            _statusLiveData.postValue(Resource.Success(message, statusResult))
        } else {
            _statusLiveData.postValue(Resource.Error("Something Went Wrong", statusResult))
        }
    }


}