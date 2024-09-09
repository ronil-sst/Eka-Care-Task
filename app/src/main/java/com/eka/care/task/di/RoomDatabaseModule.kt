package com.eka.care.task.di

import android.content.Context
import androidx.room.Room
import com.eka.care.task.room.database.EntryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): EntryDatabase {
        return Room.databaseBuilder(
            context,
            EntryDatabase::class.java,
            "ron_task.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideEntryDao(db: EntryDatabase) = db.taskDao


//    @Singleton
//    @Provides
//    fun provideTaskRepo() = TaskRepository()

}