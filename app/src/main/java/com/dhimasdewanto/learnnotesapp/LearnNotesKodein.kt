package com.dhimasdewanto.learnnotesapp

import android.app.Application
import com.dhimasdewanto.learnnotesapp.data.database.NoteDatabase
import com.dhimasdewanto.learnnotesapp.data.repositories.NoteRepoData
import com.dhimasdewanto.learnnotesapp.domain.repositories.NoteRepo
import com.dhimasdewanto.learnnotesapp.domain.use_cases.AddNoteUseCase
import com.dhimasdewanto.learnnotesapp.domain.use_cases.DeleteNoteById
import com.dhimasdewanto.learnnotesapp.domain.use_cases.GetAllNote
import com.dhimasdewanto.learnnotesapp.presentation.create.CreateViewModelFactory
import com.dhimasdewanto.learnnotesapp.presentation.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class LearnNotesKodein: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@LearnNotesKodein))

        bind() from singleton { NoteDatabase(instance()) }
        bind() from singleton { instance<NoteDatabase>().noteDao() }
        bind<NoteRepo>() with singleton { NoteRepoData(instance()) }
        bind() from singleton { GetAllNote(instance()) }
        bind() from singleton { AddNoteUseCase(instance()) }
        bind() from singleton { DeleteNoteById(instance()) }
        bind() from provider {
            HomeViewModelFactory(
                instance(),
                instance()
            )
        }
        bind() from provider {
            CreateViewModelFactory(
                instance()
            )
        }
    }
}