package com.dhimasdewanto.learnnotesapp.data.dao

import androidx.room.*
import com.dhimasdewanto.learnnotesapp.core.AppSettings
import com.dhimasdewanto.learnnotesapp.data.models.NoteModel

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${AppSettings.TABLE_NAME_NOTE}")
    fun getListNotes(): List<NoteModel>

    @Insert
    fun insertNote(newNoteModel: NoteModel)

    @Delete
    fun deleteNote(deletedNoteModel: NoteModel)
}