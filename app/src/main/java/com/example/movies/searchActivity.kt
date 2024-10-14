package com.example.movies

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.PrimaryKey
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.time.Year

class searchActivity : AppCompatActivity() {
    var bt1 :Button? = null
    var bt2 : Button? = null
    var edt2 :EditText? = null
    var tv  : TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        bt1 = findViewById<Button>(R.id.bt1)
        bt2 = findViewById<Button>(R.id.bt2)
        edt2 = findViewById<EditText>(R.id.edt2)
        tv = findViewById<TextView>(R.id.tv)
        bt2?.setOnClickListener {
            getMovies()
        }

      fun collums(){
          val Title: String

          val Year: Int
          val Rated: String
          val Released: String
          val Runtime: String
          val Genre: String
          val Director: String
          val Writer: String
          val Actors: String
          val Plot: String

      }

        fun convert(){

        }


    }

    fun getMovies(){
        val stb = StringBuilder("") // contains all of the JSON
        val stb2 = java.lang.StringBuilder("") // contains all the movie info
        var db2 = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()
        var moviesDao2 = db2.getMoviesDao()



        runBlocking {
            launch {
                withContext(Dispatchers.IO) {

                    val movie_requested = edt2?.text.toString()
                    val url =
                        URL("https://www.omdbapi.com/?t=" + movie_requested.trim() + "&plot=full&apikey=cb7cfa6c")

                    val con = url.openConnection()
                    val bf = BufferedReader(InputStreamReader(con.getInputStream()))

                    // read all the lines JSON info in a string builder
                    var line = bf.readLine()
                    while (line != null) {
                        stb.append(line)
                        line = bf.readLine()
                    }

                    if (stb.toString() == "")
                        return@withContext

                    val json = JSONObject(stb.toString())


                    val title: String = json.getString("Title") as String
                    stb2.append("Title :  ")

                    for (element in title) {
                        stb2.append(element)
                    }
                    val year: String = json.getString("Year") as String
                    stb2.append("\n")
                    stb2.append("Year :  ")

                    for (element in year) {
                        stb2.append(element)
                    }

                    val rated: String = json.getString("Rated") as String
                    stb2.append("\n")
                    stb2.append("Rated :  ")

                    for (element in rated) {
                        stb2.append(element)
                    }
                    val released: String = json.getString("Released") as String
                    stb2.append("\n")
                    stb2.append("Released :  ")

                    for (element in released) {
                        stb2.append(element)
                    }
                    val runtime: String = json.getString("Runtime") as String
                    stb2.append("\n")
                    stb2.append("Runtime :  ")

                    for (element in runtime) {
                        stb2.append(element)
                    }
                    val genre: String = json.getString("Genre") as String
                    stb2.append("\n")
                    stb2.append("Genre :  ")

                    for (element in genre) {
                        stb2.append(element)
                    }
                    val director: String = json.getString("Director") as String
                    stb2.append("\n")
                    stb2.append("Director :  ")

                    for (element in director) {
                        stb2.append(element)
                    }
                    val writer: String = json.getString("Writer") as String
                    stb2.append("\n")
                    stb2.append("Writer :  ")

                    for (element in writer) {
                        stb2.append(element)
                    }

                    val actors: String = json.getString("Actors") as String
                    stb2.append("\n")
                    stb2.append("Actors :  ")

                    for (element in actors) {
                        stb2.append(element)
                    }
                    val plot: String = json.getString("Plot") as String
                    stb2.append("\n")
                    stb2.append("Plot :  ")

                    for (element in plot) {
                        stb2.append(element)
                    }


                }
                tv?.setText(stb2)
            }
        }



    }

    fun addMoviesToDatabase(){
        val stb2 = java.lang.StringBuilder("") // contains all the movie info


        runBlocking {
            launch {
                withContext(Dispatchers.IO){


                }



            }
        }


    }


}