package com.example.rappichallenge.dagger

import com.example.rappichallenge.RappiTestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    Providers::class,
    ActivityModules::class,
    ViewModelsModule::class,
    AndroidInjectionModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RappiTestApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: RappiTestApplication)
}