package com.eka.care.task.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eka.care.task.repository.RoomRepository
import com.eka.care.task.room.models.EntryModel
import com.eka.care.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class EntryListVM @Inject constructor(private val repository: RoomRepository) : ViewModel() {


    private val _entries = MutableStateFlow<List<EntryModel>>(emptyList())
    val entries: StateFlow<List<EntryModel>> = _entries

    private val _showLoading = MutableStateFlow(true)
    val showLoading: StateFlow<Boolean> = _showLoading

    init {
        getAllEntries()
    }
     fun getAllEntries() {
        _showLoading.value=true
        repository.getAllEntries() {
            viewModelScope.launch {
                it.data?.collectLatest {
                    _entries.value = it
                    _showLoading.value=false
                }
            }
        }
    }

}