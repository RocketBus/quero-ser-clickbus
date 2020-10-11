package com.clickbus.moviesdbtest.movies.view.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.models.MovieListPageResult
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeAdapter(private val movies: MovieListPageResult) : RecyclerView.Adapter<HomeAdapter.ViewHolderMovies> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovies {
        val view = LayoutInflater.
        from(parent.context).
        inflate(R.layout.movies_item,
                parent,
                false)

        return ViewHolderMovies(view)
    }

    override fun getItemCount() = movies.movieList.count()

    class ViewHolderMovies(view: View):RecyclerView.ViewHolder(view){
        val title:TextView = itemView.findViewById(R.id.txtTitle)
        val original:TextView = itemView.findViewById(R.id.txtOriginal)
    }


    override fun onBindViewHolder(holder: ViewHolderMovies, position: Int) {
        holder.title.text = movies.movieList[position].title
        holder.original.text = movies.movieList[position].originalTitle
    }
}