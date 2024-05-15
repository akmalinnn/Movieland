package com.movieland.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.catnip.kokomputer.utils.proceedWhen
import com.movieland.R
import com.movieland.data.model.Movie
import com.movieland.databinding.FragmentHomeBinding
import com.movieland.presentation.home.adapter.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()

    private val nowPlayingAdapter: MovieAdapter by lazy {
        MovieAdapter { movie ->
            Toast.makeText(context, "Clicked on ${movie.desc}", Toast.LENGTH_SHORT).show()
        }
    }

    private val popularAdapter: MovieAdapter by lazy {
        MovieAdapter { movie ->
            Toast.makeText(context, "Clicked on ${movie.desc}", Toast.LENGTH_SHORT).show()
        }
    }

    private val topRatedAdapter: MovieAdapter by lazy {
        MovieAdapter { movie ->
            Toast.makeText(context, "Clicked on ${movie.desc}", Toast.LENGTH_SHORT).show()
        }
    }

    private val upComingAdapter: MovieAdapter by lazy {
        MovieAdapter { movie ->
            Toast.makeText(context, "Clicked on ${movie.desc}", Toast.LENGTH_SHORT).show()
        }
    }

    private var nowPlayingMovies: List<Movie> = emptyList()
    private var popularMovies: List<Movie> = emptyList()
    private var topRatedMovies: List<Movie> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupBanner()
        setupMovieNowPlayingList()
        setupMoviePopularList()
        setupMovieTopRatedList()
        setupMovieUpComingList()
        proceedMovieNowPlaying()
        proceedMoviePopular()
        proceedMovieTopRated()
        proceedMovieUpComing()
        combineAndSetBannerMovies()
    }

    private fun setupBanner() {
        binding.layoutBanner.ivBg.isVisible = false
        binding.layoutBanner.tvDesc.isVisible = false
        binding.layoutBanner.tvTitle.isVisible = false
    }

    override fun onResume() {
        super.onResume()
        combineAndSetBannerMovies()
    }

    private fun setupMovieBanner(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            binding.layoutBanner.ivBg.isVisible = true
            binding.layoutBanner.tvDesc.isVisible = true
            binding.layoutBanner.tvTitle.isVisible = true
            val randomMovie = movies.random()
            bindBannerMovie(randomMovie)
        }
    }

    private fun bindBannerMovie(movie: Movie) {
        binding.layoutBanner.ivBg.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
        binding.layoutBanner.tvTitle.text = movie.title
        binding.layoutBanner.tvDesc.text = movie.desc
    }

    private fun setupMovieNowPlayingList() {
        binding.rvNowPlaying.apply {
            adapter = nowPlayingAdapter
        }
    }

    private fun setupMoviePopularList() {
        binding.rvPopular.apply {
            adapter = popularAdapter
        }
    }

    private fun setupMovieTopRatedList() {
        binding.rvTopRated.apply {
            adapter = topRatedAdapter
        }
    }

    private fun setupMovieUpComingList() {
        binding.rvUpcomingMovies.apply {
            adapter = upComingAdapter
        }
    }

    private fun proceedMovieNowPlaying() {
        homeViewModel.getMoviesNowPlaying().observe(viewLifecycleOwner) {
            it?.proceedWhen(
                doOnLoading = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateNowPlaying.tvError.isVisible = false
                },
                doOnSuccess = {
                    binding.rvNowPlaying.isVisible = true
                    it.payload?.let { data ->
                        bindMovieNowPlayingList(data)
                        combineAndSetBannerMovies()
                    }
                    binding.layoutContentStateNowPlaying.tvError.isVisible = false
                },
                doOnError = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateNowPlaying.tvError.isVisible = true
                    binding.layoutContentStateNowPlaying.tvError.text = it.exception?.message.orEmpty()
                },
                doOnEmpty = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateNowPlaying.tvError.isVisible = false
                    binding.layoutContentStateNowPlaying.tvError.text = getString(R.string.text_empty)
                },
            )
        }
    }

    private fun proceedMoviePopular() {
        homeViewModel.getMoviesPopular().observe(viewLifecycleOwner) {
            it?.proceedWhen(
                doOnLoading = {
                    binding.rvPopular.isVisible = false
                    binding.layoutContentStatePopular.tvError.isVisible = false
                },
                doOnSuccess = {
                    binding.rvPopular.isVisible = true
                    it.payload?.let { data ->
                        bindMoviePopularList(data)
                        combineAndSetBannerMovies()
                    }
                    binding.layoutContentStatePopular.tvError.isVisible = false
                },
                doOnError = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStatePopular.tvError.isVisible = true
                    binding.layoutContentStatePopular.tvError.text = it.exception?.message.orEmpty()
                },
                doOnEmpty = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStatePopular.tvError.isVisible = false
                    binding.layoutContentStatePopular.tvError.text = getString(R.string.text_empty)
                },
            )
        }
    }

    private fun proceedMovieTopRated() {
        homeViewModel.getMoviesTopRated().observe(viewLifecycleOwner) {
            it?.proceedWhen(
                doOnLoading = {
                    binding.rvTopRated.isVisible = false
                    binding.layoutContentStateTop.tvError.isVisible = false
                },
                doOnSuccess = {
                    binding.rvTopRated.isVisible = true
                    it.payload?.let { data ->
                        bindMovieTopRatedList(data)
                        combineAndSetBannerMovies()
                    }
                    binding.layoutContentStateTop.tvError.isVisible = false
                },
                doOnError = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateTop.tvError.isVisible = true
                    binding.layoutContentStateTop.tvError.text = it.exception?.message.orEmpty()
                },
                doOnEmpty = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateTop.tvError.isVisible = false
                    binding.layoutContentStateTop.tvError.text = getString(R.string.text_empty)
                },
            )
        }
    }

    private fun proceedMovieUpComing() {
        homeViewModel.getMoviesUpComing().observe(viewLifecycleOwner) {
            it?.proceedWhen(
                doOnLoading = {
                    binding.rvUpcomingMovies.isVisible = false
                    binding.layoutContentStateUpcoming.tvError.isVisible = false
                },
                doOnSuccess = {
                    binding.rvUpcomingMovies.isVisible = true
                    it.payload?.let { data ->
                        bindMovieUpComingList(data)
                    }
                    binding.layoutContentStateUpcoming.tvError.isVisible = false
                },
                doOnError = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateUpcoming.tvError.isVisible = true
                    binding.layoutContentStateUpcoming.tvError.text = it.exception?.message.orEmpty()
                },
                doOnEmpty = {
                    binding.rvNowPlaying.isVisible = false
                    binding.layoutContentStateUpcoming.tvError.isVisible = false
                    binding.layoutContentStateUpcoming.tvError.text = getString(R.string.text_empty)
                },
            )
        }
    }

    private fun bindMovieNowPlayingList(data: List<Movie>) {
        nowPlayingMovies = data
        nowPlayingAdapter.submitData(data)
    }

    private fun bindMoviePopularList(data: List<Movie>) {
        popularMovies = data
        popularAdapter.submitData(data)
    }

    private fun bindMovieTopRatedList(data: List<Movie>) {
        topRatedMovies = data
        topRatedAdapter.submitData(data)
    }

    private fun bindMovieUpComingList(data: List<Movie>) {
        upComingAdapter.submitData(data)
    }

    private fun combineAndSetBannerMovies() {
        val combinedMovies = nowPlayingMovies + popularMovies + topRatedMovies
        if (combinedMovies.isNotEmpty()) {
            setupMovieBanner(combinedMovies)
        }
    }
}