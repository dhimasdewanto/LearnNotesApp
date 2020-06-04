package com.dhimasdewanto.learnnotesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dhimasdewanto.learnnotesapp.core.AppSettings
import com.dhimasdewanto.learnnotesapp.data.dao.NoteDao
import com.dhimasdewanto.learnnotesapp.data.models.NoteModel

@Database(
    entities = [NoteModel::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile // immediately visible to other thread after created.
        private var instance: NoteDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            AppSettings.DB_NAME
        ).build()
    }
}