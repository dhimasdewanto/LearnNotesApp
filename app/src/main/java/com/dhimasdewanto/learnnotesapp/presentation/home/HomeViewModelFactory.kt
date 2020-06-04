package com.dhimasdewanto.learnnotesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhimasdewanto.learnnotesapp.domain.use_cases.DeleteNoteById
import com.dhimasdewanto.learnnotesapp.domain.use_cases.GetAllNote

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val getAllNote: GetAllNote,
    private val deleteNoteById: DeleteNoteById
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>) : T {
        return HomeViewModel(
            getAllNote,
            deleteNoteById
        ) as T
    }
}