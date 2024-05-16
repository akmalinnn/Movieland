package com.movieland.presentation.viewAll

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.movieland.databinding.ActivityViewAllBinding
import com.movieland.presentation.viewAll.adapter.ViewAllAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ViewAllActivity : AppCompatActivity() {
    private val binding : ActivityViewAllBinding by lazy {
        ActivityViewAllBinding.inflate(layoutInflater)
    }
    private val viewAllViewModel : ViewAllViewModel by viewModel {
        parametersOf(intent.extras)
    }

    private val viewAllAdapter: ViewAllAdapter by lazy {
        ViewAllAdapter {
        }
    }

    companion object {
        const val HEADER = "HEADER"

        fun startActivity(
            context: Context,
            header: String,
        ) {
            val intent = Intent(context, ViewAllActivity::class.java)
            intent.putExtra(HEADER, header)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupMovieTopRatedList()
        observeData()
        setClickListener()
    }

    private fun observeData() {
        viewAllViewModel.header.let {
            binding.tvHeaderViewAll.text = it
            when (it) {
                "Top Rated" -> getTopRated()
                "Popular" -> getPopular()
                "Upcoming" -> getUpcoming()
                "Now Playing" -> getNowPlaying()
            }
        }
    }

    private fun setClickListener() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupMovieTopRatedList() {
        binding.rvMovieList.apply {
            adapter = viewAllAdapter
        }
    }

    private fun getTopRated() {
        lifecycleScope.launch {
            viewAllViewModel.topRating().collectLatest { pagingData ->
                viewAllAdapter.submitData(pagingData)
            }
        }
    }

    private fun getUpcoming() {
        lifecycleScope.launch {
            viewAllViewModel.upcoming().collectLatest { pagingData ->
                viewAllAdapter.submitData(pagingData)
            }
        }
    }

    private fun getNowPlaying() {
        lifecycleScope.launch {
            viewAllViewModel.nowPlaying().collectLatest { pagingData ->
                viewAllAdapter.submitData(pagingData)
            }
        }
    }

    private fun getPopular() {
        lifecycleScope.launch {
            viewAllViewModel.getPopularMovie().collectLatest { pagingData ->
                viewAllAdapter.submitData(pagingData)
            }
        }
    }
}