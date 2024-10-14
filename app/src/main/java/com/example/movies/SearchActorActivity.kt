package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ActorScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class SearchActorActivity : AppCompatActivity() {

    var actortxt : EditText? = null
    var textMovies  : TextView? = null
    var seacrhBt     : Button?   = null
    val actor = actortxt?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_actor)


        actortxt = findViewById<EditText>(R.id.actorText)

        textMovies = findViewById<TextView>(R.id.txtMovies)

        seacrhBt = findViewById<Button>(R.id.searchBt)
        var db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()

        var moviesDao = db.getMoviesDao()


        var s: String
        seacrhBt?.setOnClickListener {
            runBlocking {
                launch {

                    val allMovies =moviesDao.getAll()

                    s = actortxt?.text.toString().trim()

                    textMovies?.setText("")

                    for (m in allMovies){
                        if(m!!.Actors!!.contains(s, true))
                            textMovies?.append(  "Movie Title: "   + m.Title + "\n " )

                    }
                }
            }
        }







    }



    }



