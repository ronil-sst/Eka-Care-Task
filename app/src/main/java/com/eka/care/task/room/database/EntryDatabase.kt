package com.eka.care.task.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eka.care.entry.room.converters.TypeConverter
import com.eka.care.task.room.dao.EntryDao
import com.eka.care.task.room.models.EntryModel

@Database(
    entities = [EntryModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class EntryDatabase : RoomDatabase() {

    abstract val taskDao : EntryDao

//    companion object {
//        @Volatile
//        private var INSTANCE: TaskDatabase? = null
//        fun getInstance(context: Context): TaskDatabase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    TaskDatabase::class.java,
//                    "task_db"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//
//        }
//    }

}