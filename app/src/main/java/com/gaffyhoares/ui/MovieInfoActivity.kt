package com.gaffyhoares.ui

import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.gaffyhoares.R
import com.gaffyhoares.data.network.response.MoviesResult
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_info.*
import net.gahfy.feedme.utils.IMG_BASE_URL

class MovieInfoActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

        setUiElements()
    }

    fun setUiElements() {
        val movie_detail = Gson().fromJson<MoviesResult>(
            intent.extras!!.getString("movie"),
            MoviesResult::class.java
        )
        if (movie_detail != null) {

            setBackArrowEnabled(toolbar, movie_detail.original_title, true)
            release_date.text = movie_detail.release_date
            avg_vote.text = movie_detail.vote_average.toString()
            overview.text = movie_detail.overview
            movie_id.text = movie_detail.id.toString()
            Glide.with(this)
                .load(IMG_BASE_URL + movie_detail.poster_path)
                .into(cover_img)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> {
            }
        }
        return true
    }
}
