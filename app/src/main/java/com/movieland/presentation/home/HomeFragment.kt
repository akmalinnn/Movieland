package com.movieland.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.catnip.kokomputer.utils.proceedWhen
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.movieland.R
import com.movieland.data.model.Movie
import com.movieland.databinding.FragmentDetailBinding
import com.movieland.databinding.FragmentHomeBinding
import com.movieland.presentation.home.adapter.MovieAdapter
import com.movieland.presentation.home.adapter.OnItemClickedListener
import com.movieland.presentation.viewAll.ViewAllActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.movieland.presentation.detail.DetailFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        observeData()
        setClickAction()
        setClickListenerViewAllAction()

    }

    private fun setClickListenerViewAllAction() {
        val intent = Intent(requireContext(), ViewAllActivity::class.java)

        binding.ivMoreTopRated.setOnClickListener {
            intent.putExtra("HEADER", "Top Rated")
            startActivity(intent)
        }
        binding.ivMorePopular.setOnClickListener {
            intent.putExtra("HEADER", "Popular")
            startActivity(intent)
        }
        binding.ivMoreUpcomingMovies.setOnClickListener {
            intent.putExtra("HEADER", "Upcoming")
            startActivity(intent)
        }
        binding.ivMoreNowPlaying.setOnClickListener {
            intent.putExtra("HEADER", "Now Playing")
            startActivity(intent)

        }
    }

    private fun observeData() {
        observeMovieNowPlayingData()
        observeMoviePopularData()
        observeMovieUpcomingData()
        observeTopRatedData()
    }


    private fun setClickAction() {
        binding.ivMoreNowPlaying.setOnClickListener {

        }
        binding.ivMorePopular.setOnClickListener {

        }
        binding.ivMoreUpcomingMovies.setOnClickListener {

        }
        binding.ivMoreTopRated
    }


    private fun onItemClick(movie: Movie) {
        val detailFragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DetailFragment.EXTRAS_MOVIE, movie)
            }
        }

        detailFragment.show(parentFragmentManager, DetailFragment::class.java.simpleName)
    }



    private fun bindBannerMovieData(movie: List<Movie>) {
        val randomMovieIndex = movie.indices.random()
        val randomMovie = movie[randomMovieIndex]

        binding.layoutBanner.tvMovieTittle.text = randomMovie.title
        binding.layoutBanner.tvMovieDescription.text = randomMovie.desc
        binding.layoutBanner.ivBanner.load("https://image.tmdb.org/t/p/w500/${randomMovie.image}") {
            crossfade(1000)
        }

        binding.layoutBanner.btnShare.setOnClickListener {

            val message = getString(R.string.https_www_themoviedb_org_movie, randomMovie.id)
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = getString(R.string.text_html)
            }
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))

        }

        binding.layoutBanner.btnInfo.setOnClickListener {

            val detailFragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DetailFragment.EXTRAS_MOVIE, randomMovie)
                }
            }

            detailFragment.show(parentFragmentManager, DetailFragment::class.java.simpleName)

        }
    }

    private fun startRandomMovieBannerUpdater(movies: List<Movie>) {
        lifecycleScope.launch {
            while (true) {
                bindBannerMovieData(movies)
                delay(10000)
            }
        }
    }

    private fun setupAdapters() {
        val itemClickListener = object : OnItemClickedListener<Movie> {
            override fun onItemClicked(item: Movie) {
                onItemClick(item)
            }
        }

        nowPlayingAdapter = MovieAdapter(itemClickListener)
        popularAdapter = MovieAdapter(itemClickListener)
        upcomingAdapter = MovieAdapter(itemClickListener)
        topRatedAdapter = MovieAdapter(itemClickListener)

        binding.rvMovieNowPlaying.adapter = nowPlayingAdapter
        binding.rvMoviePopular.adapter = popularAdapter
        binding.rvMovieUpcomingMovies.adapter = upcomingAdapter
        binding.rvMovieTopRated.adapter = topRatedAdapter
    }

    private fun observeMovieNowPlayingData() {
        homeViewModel.getMovieNowPlaying().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoadingNowPlaying.isVisible = false
                    binding.rvMovieNowPlaying.isVisible = true
                    it.payload?.let { data ->
                        startRandomMovieBannerUpdater(data)
                        nowPlayingAdapter.submitData(data)
                    }
                },
                doOnLoading = {
                    binding.pbLoadingNowPlaying.isVisible = true
                    binding.rvMovieNowPlaying.isVisible = false
                },
                doOnError = {
                    binding.pbLoadingNowPlaying.isVisible = false
                },
            )
        }
    }

    private fun observeMoviePopularData() {
        homeViewModel.getMoviePopular().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoadingPopular.isVisible = false
                    binding.rvMoviePopular.isVisible = true
                    it.payload?.let { data ->
                        popularAdapter.submitData(data)
                    }
                },
                doOnLoading = {
                    binding.pbLoadingPopular.isVisible = true
                    binding.rvMoviePopular.isVisible = false
                },
                doOnError = {
                    binding.pbLoadingPopular.isVisible = false
                },
            )
        }
    }

    private fun observeMovieUpcomingData() {
        homeViewModel.getMovieUpcoming().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoadingUpcomingMovies.isVisible = false
                    binding.rvMovieUpcomingMovies.isVisible = true
                    it.payload?.let { data ->
                        upcomingAdapter.submitData(data)
                    }
                },
                doOnLoading = {
                    binding.pbLoadingUpcomingMovies.isVisible = true
                    binding.rvMovieUpcomingMovies.isVisible = false
                },
                doOnError = {
                    binding.pbLoadingUpcomingMovies.isVisible = false
                },
            )
        }
    }

    private fun observeTopRatedData() {
        homeViewModel.getMovieTopRating().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoadingTopRated.isVisible = false
                    binding.rvMovieTopRated.isVisible = true
                    it.payload?.let { data ->
                        topRatedAdapter.submitData(data)
                    }
                },
                doOnLoading = {
                    binding.pbLoadingTopRated.isVisible = true
                    binding.rvMovieTopRated.isVisible = false
                },
                doOnError = {
                    binding.pbLoadingTopRated.isVisible = false
                },
            )
        }
    }
}