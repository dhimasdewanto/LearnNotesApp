package com.dhimasdewanto.learnnotesapp.domain.use_cases

import com.dhimasdewanto.learnnotesapp.core.Failure
import com.dhimasdewanto.learnnotesapp.core.Ok
import com.dhimasdewanto.learnnotesapp.core.Res
import com.dhimasdewanto.learnnotesapp.core.UseCase
import com.dhimasdewanto.learnnotesapp.domain.repositories.NoteRepo

class DeleteNoteById(
    private val repo: NoteRepo
): UseCase<Unit, Int> {
    override suspend fun call(params: Int): Res<Unit, Failure> {
        val isValidate = validate(params)

        if (isValidate is Failure) {
            return isValidate
        }

        return repo.deleteNoteById(params)
    }

    override suspend fun validate(params: Int): Res<Unit, Failure> {
        return Ok(Unit)
    }
}