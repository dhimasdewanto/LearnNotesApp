package com.dhimasdewanto.learnnotesapp.core

abstract class Failure {
    abstract val message: String
}

class DefaultFailure(override val message: String) : Failure()