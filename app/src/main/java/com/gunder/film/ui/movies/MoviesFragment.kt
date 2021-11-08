package com.gunder.film.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunder.film.R
import com.gunder.film.viewmodel.ViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovies: RecyclerView = view.findViewById(R.id.rv_movies)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val moviesAdapter = MoviesAdapter()

        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.setHasFixedSize(true)
        rvMovies.adapter = moviesAdapter

        progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance()
        moviesViewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

        moviesViewModel.getMovies().observe(viewLifecycleOwner, { listMovie ->
            moviesAdapter.setData(listMovie)
            progressBar.visibility = View.GONE
        })
    }
}