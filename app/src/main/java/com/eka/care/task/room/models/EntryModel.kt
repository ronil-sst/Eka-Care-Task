package com.eka.care.task.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity()
data class EntryModel(
    @ColumnInfo(name = "taskTitle")
    val name: String,
    val age: String,
    val dob: String,
    val address: String,
) :Serializable{
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0
}
