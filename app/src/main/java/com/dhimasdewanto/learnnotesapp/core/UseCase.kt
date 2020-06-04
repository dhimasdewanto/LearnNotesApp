package com.dhimasdewanto.learnnotesapp.core

interface  UseCase<Type, Params> {
    suspend fun call(params: Params) : Res<Type, Failure>
    suspend fun validate(params: Params) : Res<Unit, Failure>
}

