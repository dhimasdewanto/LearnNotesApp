package com.dhimasdewanto.learnnotesapp.domain.use_cases

import com.dhimasdewanto.learnnotesapp.core.*
import com.dhimasdewanto.learnnotesapp.domain.entities.Note
import com.dhimasdewanto.learnnotesapp.domain.repositories.NoteRepo

class AddNoteUseCase(
    private val repo: NoteRepo
) : UseCase<Unit, AddNoteParam> {
    override suspend fun call(params: AddNoteParam): Res<Unit, Failure> {
        if (validate(params) is Err) {
            return validate(params) as Err
        }

        return repo.addNote(Note(
            title = params.title,
            content = params.content
        ))
    }

    override suspend fun validate(params: AddNoteParam): Res<Unit, Failure> {
        if (params.title.isEmpty() || params.content.isEmpty()) {
            return Err(InvalidCharacterLengthFailure("Title or contents is empty."))
        }

        if (params.title.length < 3) {
            return Err(InvalidCharacterLengthFailure("Minimum title is 3 characters."))
        }

        if (params.content.length < 3) {
            return Err(InvalidCharacterLengthFailure("Minimum content is 3 characters."))
        }

        return Ok(Unit)
    }
}

data class AddNoteParam(
    val title: String,
    val content: String
)

class InvalidCharacterLengthFailure(override val message: String) : Failure()