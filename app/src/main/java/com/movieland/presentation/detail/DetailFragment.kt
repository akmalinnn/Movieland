package com.movieland.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.load
import com.catnip.kokomputer.utils.proceedWhen
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.movieland.R
import com.movieland.data.model.Movie
import com.movieland.data.model.MovieDetail
import com.movieland.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailBinding
    private var detail: MovieDetail? = null
    private val viewModel: DetailViewModel by viewModel {
        parametersOf(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setClickAction()
    }


    companion object {
        const val EXTRAS_MOVIE = "EXTRAS_MOVIE"
    }

    private fun setClickAction() {
        binding.moveDetail.btnToShare.setOnClickListener {
            detail?.let { movie ->
                shareMovie(movie)
            }
        }

        var isAddedToList = false // declare outside the click listener

        binding.moveDetail.btnAddToFavorite.setOnClickListener {
            val favoriteButton = binding.moveDetail.btnAddToFavorite
            detail?.let { movie ->
                if (!isAddedToList) {
                    favoriteButton.setImageResource(R.drawable.ic_done)
                    isAddedToList = true
                } else {
                    favoriteButton.setImageResource(R.drawable.ic_add_mylist)
                    isAddedToList = false
                }
            }
        }



    }


    private fun observeData() {
        viewModel.movie?.id.let {
            it?.let { it ->
                viewModel.getDetail(it).observe(viewLifecycleOwner) { result ->
                    result.proceedWhen(
                        doOnSuccess = { success ->
                            success.payload.let { detailMovie ->
                                detail = detailMovie
                                binding.movieTop.shimmerLayout.isVisible = false
                                binding.movieTop.ivMovieImageBg.isVisible = true
                                binding.movieTop.ivMovieImage.isVisible = true
                                setBind(detailMovie)

                            }
                        },
                        doOnLoading = {
                            binding.movieTop.shimmerLayout.isVisible = true
                        },
                        doOnError = {
                            binding.movieTop.ivMovieImageBg.isVisible = false
                            binding.movieTop.ivMovieImage.isVisible = false
                        },
                    )
                }
            }
        }
    }


    private fun setBind(movie: MovieDetail?) {
        val formattedRating =
            movie?.let { String.format(getString(R.string.rating_format), it.rating) }
        movie?.let { _ ->
            binding.movieTop.ivMovieImageBg.load(
                getString(
                    R.string.https_image_tmdb_org_t_p_w500,
                    movie.banner
                )
            )
            binding.movieTop.ivMovieImage.load(
                getString(
                    R.string.https_image_tmdb_org_t_p_w500,
                    movie.image
                )
            )
            binding.movieTop.tvMovieTitle.text = movie.title
            binding.movieTop.tvMovieDate.text = movie.date
            binding.movieTop.tvMovieRating.text = getString(R.string.star_icon, formattedRating)
            binding.movieDescription.tvMovieDesc.text = movie.desc
        }
    }

    private fun shareMovie(movie: MovieDetail?) {
        val message = getString(R.string.https_www_themoviedb_org_movie, movie?.id)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = getString(R.string.text_html)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
    }

}
