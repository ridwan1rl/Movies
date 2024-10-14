package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var tv : TextView
    lateinit var button2 : Button
    lateinit var button1 : Button
    lateinit var bt3 : Button
    lateinit var button4 : Button
    lateinit var edittext : EditText
    val stb = StringBuilder("") // contains all of the JSON
    var stb2 = java.lang.StringBuilder("") // contains all the movie info
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<TextView>(R.id.tv)
        button2 = findViewById<Button>(R.id.button2)
        button1 =findViewById<Button>(R.id.button1)
        bt3 = findViewById<Button>(R.id.bt3)
        button4 = findViewById<Button>(R.id.Search2)
        edittext = findViewById<EditText>(R.id.Qsearch)



        var db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()

        var moviesDao = db.getMoviesDao()







        button2.setOnClickListener {
            val x = Intent (this, searchActivity::class.java)
            startActivity(x)
        }

        bt3.setOnClickListener {
            val y = Intent(this, SearchActorActivity::class.java)
            startActivity(y)
        }




        button1.setOnClickListener {
    runBlocking {
        launch {
            val m1 = Movies(
                "The Shawshank Redemption",
                1994,
                "R",
                "14 Oct 1994",
                "142 min",
                "Drama",
                "Frank Darabont",
                "Stephen King, Frank Darabont",
                "Tim Robbins, Morgan Freeman, Bob Gunton",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
            )
            val m2 = Movies(
                "Batman: The Dark Knight Returns :Part 1",
                2012,
                "PG-13",
                "25 Sep 2012",
                "76 min",
                "Animation, Action, Crime, Drama, Thriller",
                "Jay Oliva",
                "Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob Goodman",
                "Peter Weller, Ariel Winter, David Selby, Wade Williams",
                "Batman has not been seen for ten years. A new breed of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back into the cape and cowl. But, does he still have what it takes to fight crime in a new era?"
            )
            val m3 = Movies(
                "The Lord of the Rings: The Return of the King",
                2003,
                "PG-13",
                "17 Dec 2003",
                "201 min",
                "Action, Adventure, Drama",
                "Peter Jackson",
                "J.R.R. Tolkien, Fran Walsh, Philippa Boyens",
                "Elijah Wood, Viggo Mortensen, Ian McKellen",
                "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
            )
            val m4 = Movies(
                "Inception",
                2010,
                "PG-13",
                "16 Jul 2010",
                "148 min",
                "Action, Adventure, Sci-Fi",
                "Christopher Nolan",
                "Christopher Nolan",
                "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
            )
            val m5 = Movies(
                "The Matrix",
                1999,
                "R",
                "31 Mar 1999",
                "136 min",
                "Action, Sci-Fi",
                "Lana Wachowski, Lilly Wachowski",
                "Lilly Wachowski, Lana Wachowski",
                "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence."
            )

            moviesDao.insertMovies(m1, m2, m3, m4, m5)

        }
    } }

        button4.setOnClickListener {
            quickSearch()
        }

    }
    fun quickSearch(){
        runBlocking {
            launch {
                withContext(Dispatchers.IO){
                    val movie_requested = edittext.text.toString().trim()
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

                    for (element in stb) {
                        if (title!!.contains(movie_requested, true))
                            stb2.append(element)
                    }

                }
                tv.setText(stb2)

            }
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("stb2" , stb2.toString())
    }


}