package com.example.rappichallenge.dagger

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rappichallenge.repository.local.AppDataBase
import com.example.rappichallenge.repository.local.RestaurantsDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule() {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE restaurants RENAME TO rests")
            }
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDataBase = Room.databaseBuilder(app,
        AppDataBase::class.java, "app_db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideRestaurantsDao(database: AppDataBase): RestaurantsDAO = database.restaurantsDAO()
}