package com.gunder.film.ui.tvshow

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

class TvShowFragment : Fragment() {
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTvShow: RecyclerView = view.findViewById(R.id.rv_tv_show)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val tvShowAdapter = TvShowAdapter()

        rvTvShow.layoutManager = GridLayoutManager(context, 2)
        rvTvShow.setHasFixedSize(true)
        rvTvShow.adapter = tvShowAdapter

        progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance()
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        tvShowViewModel.getTvShow().observe(viewLifecycleOwner, { listTvShow ->
            tvShowAdapter.setTvShow(listTvShow)
            progressBar.visibility = View.GONE
        })
    }
}