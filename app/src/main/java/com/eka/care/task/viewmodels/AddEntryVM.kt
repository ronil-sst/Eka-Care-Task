package com.eka.care.task.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.eka.care.task.repository.RoomRepository
import com.eka.care.task.room.models.EntryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEntryVM @Inject constructor(private val repository: RoomRepository) : ViewModel() {

    fun addEntry(entry: EntryModel) = repository.insertTask(entry) {
        Log.e("addEntry", ": ${it.status}       ${it.message}")
    }

}