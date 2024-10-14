package com.example.movies

import androidx.room.Database
import androidx.room.RoomDatabase


    @Database(entities = [Movies::class], version=1)
    abstract class AppDatabase: RoomDatabase() {
        abstract fun getMoviesDao(): MoviesDao

    }
