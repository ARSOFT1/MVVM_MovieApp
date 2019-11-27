package com.gaffyhoares.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gaffyhoares.MyApplication
import com.gaffyhoares.R
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.ui.BaseActivity
import com.gaffyhoares.ui.MovieInfoActivity
import com.gaffyhoares.viewmodels.ViewModelProviderFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var year: String = "2019"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackArrowEnabled(toolbar, "", false)

        (application as MyApplication).appComponent?.inject(this@HomeActivity)

        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)
        getMoviesList()
        swiperefresh.setOnRefreshListener {
            getMoviesList()
        }
    }

    private fun getMoviesList() {
        swiperefresh.isRefreshing = true

        val moviesData = mainViewModel.getMoviesList(year)
        moviesData.observe(this, object : Observer<List<MoviesResult>> {
            override fun onChanged(t: List<MoviesResult>?) {
                initView(t!!)
                swiperefresh.isRefreshing = false
            }

        })
    }

    private fun initView(movieList: List<MoviesResult>) {

        val moviesAdapter = MoviesAdapter(this, movieList)
        reyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(false)
            adapter = moviesAdapter
        }


        moviesAdapter.setListener(object : MoviesAdapter.itemClickListener {
            override fun itemClick(item: MoviesResult) {
                val intent = Intent(this@HomeActivity, MovieInfoActivity::class.java).apply {
                    putExtra("movie", Gson().toJson(item))
                }
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.year_one -> {
                year = "2019"
                getMoviesList()
                true
            }
            R.id.year_two -> {
                year = "2018"
                getMoviesList()
                true
            }
            R.id.year_three -> {
                year = "2017"
                getMoviesList()
                true
            }
            R.id.year_four -> {
                year = "2016"
                getMoviesList()
                true
            }
            R.id.year_fifth -> {
                year = "2015"
                getMoviesList()
                true
            }
            R.id.popular -> {
                getTopMovies("popular")
                true
            }
            R.id.top_rated -> {
                getTopMovies("top_rated")
                true
            }
            R.id.upcoming -> {
                getTopMovies("upcoming")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getTopMovies(movie_category: String) {
        mainViewModel.getTopRatedMovieList(movie_category)
            .observe(this, object : Observer<List<MoviesResult>> {
                override fun onChanged(t: List<MoviesResult>?) {
                    initView(t!!)
                }
            })
    }
}
