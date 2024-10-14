package com.example.movies

import android.graphics.Movie
import androidx.room.*


@Dao
interface MoviesDao {

    @Query ("Select * from Movies")
    suspend fun getAll() : List <Movies>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies (vararg movies: Movies)

    @Insert
    suspend fun insertAll (vararg movies: Movies)






}