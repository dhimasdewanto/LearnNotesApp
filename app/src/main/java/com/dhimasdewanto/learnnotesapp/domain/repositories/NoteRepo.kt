package com.dhimasdewanto.learnnotesapp.domain.repositories

import com.dhimasdewanto.learnnotesapp.core.Failure
import com.dhimasdewanto.learnnotesapp.core.Res
import com.dhimasdewanto.learnnotesapp.domain.entities.Note

interface NoteRepo {
    suspend fun getListNotes(): Res<List<Note>, Failure>
    suspend fun addNote(newNote: Note): Res<Unit, Failure>
    suspend fun deleteNoteById(noteId: Int): Res<Unit, Failure>
}