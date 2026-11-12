package com.example.buscadordepelliculas.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.buscadordepelliculas.R
import com.example.buscadordepelliculas.data.MovieApi
import com.example.buscadordepelliculas.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieId = intent.getStringExtra("MOVIE_ID") ?: ""

        if (movieId.isNotEmpty()) {
            fetchMovieDetails(movieId)
        }
    }

    private fun fetchMovieDetails(id: String) {
        val api = MovieApi.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val movieDetail = api.getMovieById(id = id)

            CoroutineScope(Dispatchers.Main).launch {
                binding.titleTextView.text = movieDetail.title
                binding.yearTextView.text = movieDetail.year
                binding.runtimeTextView.text = movieDetail.runtime
                binding.genreChip.text = movieDetail.genre
                binding.directorTextView.text = movieDetail.director
                binding.countryTextView.text = movieDetail.country
                binding.descriptionTextView.text = movieDetail.plot


                supportActionBar?.title = movieDetail.title

                Picasso.get()
                    .load(movieDetail.poster)
                    .into(binding.thumbnailImageView)
            }
        }
    }
}