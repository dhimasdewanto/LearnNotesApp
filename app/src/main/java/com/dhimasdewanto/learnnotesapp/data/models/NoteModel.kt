package com.dhimasdewanto.learnnotesapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhimasdewanto.learnnotesapp.core.AppSettings

@Entity(tableName = AppSettings.TABLE_NAME_NOTE)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val noteId: Int? = null,

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_content")
    val content: String
)