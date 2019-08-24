package com.example.rappichallenge.dagger

import android.app.Application
import com.example.rappichallenge.RappiTestApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindApplication(application: RappiTestApplication): Application
}