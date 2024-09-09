package com.eka.care.task.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.eka.care.task.repository.RoomRepository
import com.eka.care.task.room.models.EntryModel
import com.eka.care.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AddEntryVM @Inject constructor(private val repository: RoomRepository) : ViewModel() {

    fun addEntry(entry: EntryModel,callback: (Resource<Flow<EntryModel>>) -> Unit) = repository.insertTask(entry,callback)

}