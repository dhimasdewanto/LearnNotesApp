package com.dhimasdewanto.learnnotesapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhimasdewanto.learnnotesapp.core.Err
import com.dhimasdewanto.learnnotesapp.core.Ok
import com.dhimasdewanto.learnnotesapp.domain.entities.Note
import com.dhimasdewanto.learnnotesapp.domain.use_cases.DeleteNoteById
import com.dhimasdewanto.learnnotesapp.domain.use_cases.GetAllNote

class HomeViewModel(
    private val getAllNote: GetAllNote,
    private val deleteNoteById: DeleteNoteById
): ViewModel() {
    private val _listNotes = MutableLiveData<List<Note>>(emptyList())
    val listNotes: LiveData<List<Note>>
        get() = _listNotes

    suspend fun deleteById(noteId: Int) {
        deleteNoteById.call(noteId)
        refreshData()
    }

    suspend fun refreshData() {
        val result = getAllNote.call(Unit)

        if (result is Err) {
            return
        }

        _listNotes.value = (result as Ok).value
    }
}