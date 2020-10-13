package com.clickbus.moviesdbtest.movies.view.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.view.OnClick


class HomeAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<HomeAdapter.ViewHolderMovies> (){

    private lateinit var onClick: OnClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovies {
        val view = LayoutInflater.
        from(parent.context).
        inflate(R.layout.movies_item,
                parent,
                false)

        return ViewHolderMovies(view)
    }

    override fun getItemCount() = movies.size

    class ViewHolderMovies(view: View):RecyclerView.ViewHolder(view) {


        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val original: TextView = itemView.findViewById(R.id.txtTitleOriginal)
        val image: ImageView = itemView.findViewById(R.id.imgMovie)
        var popularity: TextView = itemView.findViewById(R.id.txtPopularity)
        val voteCount: TextView = itemView.findViewById(R.id.txtVoteCount)
        val voteAverage: TextView = itemView.findViewById(R.id.txtVoteAverage)

    }


    override fun onBindViewHolder(holder: HomeAdapter.ViewHolderMovies, position: Int) {
        val baseUrl = "http://image.tmdb.org/t/p/"
        val size = "w300/"
        val imageMovies = movies[position].posterPath
        val poster = "$baseUrl$size$imageMovies"
        Glide.with(holder.itemView)
                .load(poster)
                .placeholder(R.drawable.ic_movie)
                .into(holder.image)

        holder.title.text = movies[position].title
        holder.original.text = movies[position].originalTitle
        holder.popularity.text = movies[position].popularity.toString()
        holder.voteCount.text = movies[position].voteCount.toString()
        holder.voteAverage.text = movies[position].voteAverage.toString()

        val item = holder.itemView
        item.setOnClickListener{
            if(::onClick.isInitialized){
                onClick.onCellClickListener()
            }
        }

      }

    fun onClickListener(onClick: OnClick){
        this.onClick = onClick

    }
    }
