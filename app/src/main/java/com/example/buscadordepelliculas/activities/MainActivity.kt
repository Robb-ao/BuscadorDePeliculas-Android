package com.example.buscadordepelliculas.activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.buscadordepelliculas.R
import com.example.buscadordepelliculas.adapters.MovieAdapter
import com.example.buscadordepelliculas.data.Movie
import com.example.buscadordepelliculas.data.MovieApi
import com.example.buscadordepelliculas.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: MovieAdapter

    var movieList: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = MovieAdapter(movieList){
            val movie = movieList[it]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movie.imdbID)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchEditText.text.toString().trim()
                if (query.isNotEmpty()) {
                    fetchMovies(query)
                }
                true
            } else {
                false
            }
        }

        supportActionBar?.title = "Buscador de Películas"

        fetchMovies("action")

    }

    fun fetchMovies(searchTerm: String){
        val api = MovieApi.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            movieList = api.getMoviesList(searchTerm = searchTerm).movies ?: emptyList()

            CoroutineScope(Dispatchers.Main).launch {
                adapter.updateData(movieList)
            }
        }
    }

}