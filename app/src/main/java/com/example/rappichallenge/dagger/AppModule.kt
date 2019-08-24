package com.example.rappichallenge.dagger

import android.app.Application
import com.example.rappichallenge.RappiTestApplication
import com.example.rappichallenge.repository.remote.AppRepository
import com.example.rappichallenge.repository.remote.AppRepositoryImplementation
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindApplication(application: RappiTestApplication): Application

    @Binds
    abstract fun bindRepository(repositoryImplementation: AppRepositoryImplementation) : AppRepository
}