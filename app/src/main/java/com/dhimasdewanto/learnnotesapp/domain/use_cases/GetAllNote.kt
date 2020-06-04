package com.dhimasdewanto.learnnotesapp.domain.use_cases

import com.dhimasdewanto.learnnotesapp.core.*
import com.dhimasdewanto.learnnotesapp.domain.entities.Note
import com.dhimasdewanto.learnnotesapp.domain.repositories.NoteRepo

class GetAllNote(
    private val repo: NoteRepo
) : UseCase<List<Note>, Unit> {
    override suspend fun call(params: Unit): Res<List<Note>, Failure> {
        if (validate(params) is Err) {
            return validate(params) as Err
        }

        return repo.getListNotes()
    }

    override suspend fun validate(params: Unit): Res<Unit, Failure> {
        return Ok(Unit)
    }
}