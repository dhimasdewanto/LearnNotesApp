package com.dhimasdewanto.learnnotesapp.presentation.create

import androidx.lifecycle.ViewModel
import com.dhimasdewanto.learnnotesapp.domain.use_cases.AddNoteParam
import com.dhimasdewanto.learnnotesapp.domain.use_cases.AddNoteUseCase

class CreateViewModel(
    private val addNoteUseCase: AddNoteUseCase
): ViewModel() {
    suspend fun addNote(title: String, content: String) {
        addNoteUseCase.call(AddNoteParam(title, content))
    }
}