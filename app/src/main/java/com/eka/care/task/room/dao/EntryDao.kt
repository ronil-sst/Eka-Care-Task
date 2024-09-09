package com.eka.care.task.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eka.care.task.room.models.EntryModel

import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Query("""SELECT COUNT(*) FROM(SELECT * FROM EntryModel)""")
    suspend fun getTaskCount(): Int




    @Query("""SELECT * FROM EntryModel""")
    fun getAllEntries(): Flow<List<EntryModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: EntryModel): Long


    // First way
    @Delete
    suspend fun deleteTask(task: EntryModel): Int


    // Second Way
    @Query("DELETE FROM EntryModel WHERE taskId == :taskId")
    suspend fun deleteTaskUsingId(taskId: String): Int


    @Update
    suspend fun updateTask(task: EntryModel): Int


}