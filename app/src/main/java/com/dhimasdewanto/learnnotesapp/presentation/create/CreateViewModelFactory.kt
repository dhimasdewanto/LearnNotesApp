package com.dhimasdewanto.learnnotesapp.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhimasdewanto.learnnotesapp.domain.use_cases.AddNoteUseCase

@Suppress("UNCHECKED_CAST")
class CreateViewModelFactory(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>) : T {
        return CreateViewModel(
            addNoteUseCase
        ) as T
    }
}