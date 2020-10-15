package com.clickbus.moviesdbtest.movies.view.activitys


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.state.State
import com.clickbus.moviesdbtest.movies.view.OnClick
import com.clickbus.moviesdbtest.movies.view.adapters.HomeAdapter
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.models.Genre
import kotlinx.android.synthetic.main.app_toolbar.*

class MainActivity : AppCompatActivity(R.layout.activity_main), OnClick {


    private lateinit var viewModel: BaseViewModel
    private val adapter = HomeAdapter()

    private val filterGenders: ArrayList<Int> = arrayListOf()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar.apply {
            val titlebar = "Popular Movies"

            title = titlebar

            supportActionBar?.apply {
                title = titlebar
                setDisplayShowTitleEnabled(true)
            }
        }
        )
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,BaseViewModel.BaseViewModelFactory()).get(BaseViewModel::class.java)

        rclHome.adapter = adapter.also {
            it.onClickListener(this)
        }

        rclHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && !srl_movies.isRefreshing) {
                    val manager = recyclerView.layoutManager as LinearLayoutManager

                    val visibleItemCount = manager.childCount
                    val totalItemCount = manager.itemCount
                    val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        fetchMovies(filterGenders, ++page)
                    }
                }
            }
        })

        srl_movies.setOnRefreshListener {
            page = 1
            fetchMovies(filterGenders, page)
        }

        viewModel.movies.observe(this, Observer { handleState(it) })
        fetchMovies(filterGenders)


        viewModel.genders.observe(this,  Observer{
            handlerGenders(it)
        })


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onOptionsItemSelected(item.itemId)
        return true
    }

    override fun onCellClickListener(movie: Movie) {
        startActivity(MovieDetailsActivity.callMovieDetails(this@MainActivity, movie))
    }


    private fun handleState(message: State.Message<List<Movie>>) {
        when (message.status) {
            State.Status.START -> {
                if (page == 1) view_flipper.displayedChild = 0
                else srl_movies.isRefreshing = true
            }

            State.Status.COMPLETE -> {
                view_flipper.displayedChild = 1
                srl_movies.isRefreshing = false

                adapter.movies.addAll(message.data!!)
                adapter.notifyDataSetChanged()
            }

            State.Status.ERROR -> {
                if (page == 1) view_flipper.displayedChild = 2
                else srl_movies.isRefreshing = false
            }

        }
    }

    private fun handlerGenders(message: State.Message<List<Genre>>) {
        when (message.status) {
            State.Status.START -> {
            }

            State.Status.COMPLETE -> {
                val alert = AlertDialog.Builder(ContextThemeWrapper(this, R.style.MyAlertDialogTheme)).also {
                    it.setTitle("Genders")

                    val items =  message.data!!

                    val selected = BooleanArray(items.size ?: 0)
                    (0 until (items.size)).forEach {
                        selected[it] = filterGenders.indexOf(items[it].id) >= 0
                    }

                    it.setMultiChoiceItems(
                        arrayListOf<String>().also { titles ->
                            (0 until (items.size)).forEach {
                                titles.add(items[it].name)
                            }
                        }.toTypedArray(),

                        selected,

                        DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                            selected[which] = isChecked
                        }
                    )

                    it.setPositiveButton(android.R.string.ok) { dialog: DialogInterface, i: Int ->
                        filterGenders.clear()

                        selected.forEachIndexed { index, isChecked ->
                            if(isChecked) filterGenders.add(items[index].id)
                        }

                        dialog.dismiss()
                        fetchMovies(filterGenders)
                    }

                    it.setNeutralButton("Limpar") { dialog: DialogInterface, i: Int ->
                        filterGenders.clear()
                        dialog.dismiss()
                        fetchMovies(filterGenders)
                    }

                    it.setNegativeButton(android.R.string.no) { dialog: DialogInterface, i: Int ->
                        dialog.dismiss()
                    }
                }.create()

                alert.setCancelable(false)
                alert.show()
            }

            State.Status.ERROR -> {
            }

        }


    }

    private fun onOptionsItemSelected(itemId: Int) {
        when(itemId) {
            R.id.action_filter -> {
                viewModel.upGenders()
            }
        }
    }

    private fun fetchMovies(genres: ArrayList<Int>, page: Int = 1) {
        if (page == 1)
            adapter.movies.clear()
        viewModel.upMovies(genres = genres.toTypedArray(), page = page)
    }


}