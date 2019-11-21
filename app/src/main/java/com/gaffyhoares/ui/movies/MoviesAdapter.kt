package com.gaffyhoares.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gaffyhoares.R
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.databinding.MovieItemLayoutBinding
import net.gahfy.feedme.utils.IMG_BASE_URL

class MoviesAdapter(val context: Context, private val itemsList: List<MoviesResult>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var listener: itemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding = DataBindingUtil.inflate<MovieItemLayoutBinding>(
            layoutInflater!!,
            R.layout.movie_item_layout,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = itemsList.get(position)
        holder.binding.movie = item
        holder.binding.parentLayout.setOnClickListener {
            listener?.itemClick(item)
        }

        Glide.with(context)
            .load(IMG_BASE_URL + item.poster_path)
            .into(holder.binding.posterImg)
    }

    override fun getItemCount(): Int = itemsList.size

    class MovieViewHolder(val binding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface itemClickListener {
        fun itemClick(item: MoviesResult)
    }

    fun setListener(listener: itemClickListener) {
        this.listener = listener
    }
}