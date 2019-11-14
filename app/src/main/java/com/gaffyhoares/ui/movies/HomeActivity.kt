package com.gaffyhoares.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.facebook.stetho.Stetho
import com.gaffyhoares.R
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.ui.MovieInfoActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getMoviesList()
        swiperefresh.setOnRefreshListener {
            getMoviesList()
        }
    }

    private fun getMoviesList() {
        swiperefresh.isRefreshing = true
        mainViewModel.getMoviesList().observe(this, object : Observer<List<MoviesResult>> {
            override fun onChanged(movieList: List<MoviesResult>?) {

                initView(movieList!!)
                swiperefresh.isRefreshing = false
            }
        })
    }

    private fun initView(movieList: List<MoviesResult>) {

        reyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        reyclerView.setHasFixedSize(false)
        val moviesAdapter = MoviesAdapter(this, movieList)
        moviesAdapter.setListener(object : MoviesAdapter.itemClickListener {
            override fun itemClick(item: MoviesResult) {
                val jsonString = Gson().toJson(item)
                val intent = Intent(this@HomeActivity, MovieInfoActivity::class.java).apply {
                    putExtra("movie", jsonString)
                }
                startActivity(intent)
            }
        })
        reyclerView?.adapter = moviesAdapter

    }
}
