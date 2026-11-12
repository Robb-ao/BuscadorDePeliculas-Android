package com.example.buscadordepelliculas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buscadordepelliculas.data.Movie
import com.example.buscadordepelliculas.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter(
    var items: List<Movie>,
    val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.render(movie)
//        holder.binding.movieCard.setOnClickListener {
//            onItemClick(position)
//        }
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(dataSet: List<Movie>){
        items = dataSet
        notifyDataSetChanged()
    }
}

class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.descriptionTextView.text = movie.year

        Picasso.get().load(movie.poster).into(binding.thumbnailImageView)
    }
}