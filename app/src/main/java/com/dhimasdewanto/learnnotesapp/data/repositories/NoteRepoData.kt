package com.dhimasdewanto.learnnotesapp.data.repositories

import com.dhimasdewanto.learnnotesapp.core.*
import com.dhimasdewanto.learnnotesapp.data.dao.NoteDao
import com.dhimasdewanto.learnnotesapp.data.models.NoteModel
import com.dhimasdewanto.learnnotesapp.domain.entities.Note
import com.dhimasdewanto.learnnotesapp.domain.repositories.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepoData(
    private val noteDao: NoteDao
) : NoteRepo {
    override suspend fun getListNotes(): Res<List<Note>, Failure> {
        return withContext(Dispatchers.IO) {
            val result = noteDao.getListNotes()

            val listNotes = result.map { note ->
                Note(
                    id = note.noteId,
                    title = note.title,
                    content = note.content
                )
            }.toList()

            return@withContext Ok(listNotes)
        }
    }

    override suspend fun addNote(newNote: Note): Res<Unit, Failure> {
        return withContext(Dispatchers.IO) {
            noteDao.insertNote(
                NoteModel(
                    title = newNote.title,
                    content = newNote.content
                )
            )

            return@withContext Ok(Unit)
        }
    }

    @Suppress("FoldInitializerAndIfToElvis")
    override suspend fun deleteNoteById(noteId: Int): Res<Unit, Failure> {
        return withContext(Dispatchers.IO) {
            val getListNotes = noteDao.getListNotes()
            val deletedNote = getListNotes.find {
                it.noteId == noteId
            }

            if (deletedNote == null) {
                return@withContext Err(DefaultFailure(
                    "Note ID not found"
                ))
            }

            noteDao.deleteNote(deletedNote)

            return@withContext Ok(Unit)
        }
    }
}