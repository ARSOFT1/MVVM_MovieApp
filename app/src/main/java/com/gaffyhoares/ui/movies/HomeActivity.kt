package com.gaffyhoares.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gaffyhoares.MyApplication
import com.gaffyhoares.R
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.ui.MovieInfoActivity
import com.gaffyhoares.viewmodels.ViewModelProviderFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent?.inject(this@HomeActivity)
        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)

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

        val moviesAdapter = MoviesAdapter(this, movieList)
        reyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(false)
            adapter = moviesAdapter
        }


        moviesAdapter.setListener(object : MoviesAdapter.itemClickListener {
            override fun itemClick(item: MoviesResult) {
                val jsonString = Gson().toJson(item)
                val intent = Intent(this@HomeActivity, MovieInfoActivity::class.java).apply {
                    putExtra("movie", jsonString)
                }
                startActivity(intent)
            }
        })
    }
}
