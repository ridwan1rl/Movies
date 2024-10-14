package com.example.movies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movies(

    @PrimaryKey val Title: String,

    val Year: Int,
    val Rated: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String


)






