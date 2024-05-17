package com.movieland.presentation.myList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.catnip.kokomputer.utils.proceedWhen
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.movieland.R
import com.movieland.data.model.Movie
import com.movieland.databinding.FragmentDetailBinding
import com.movieland.databinding.FragmentMyListBinding
import com.movieland.presentation.myList.adapter.MyListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : Fragment() {
    private lateinit var binding: FragmentMyListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val MyListviewModel: MyListViewModel by viewModel()

    private val mylistAdapter : MyListAdapter by lazy {
        MyListAdapter { favorite ->
            detailMovie(favorite)
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        observeData()
        setClickListener()
    }



    private fun setupList() {
        binding.rvMyList.adapter = this@MyListFragment.mylistAdapter
    }

    private fun observeData() {
        MyListviewModel.getAllFavorites().observe(viewLifecycleOwner) {
            it.proceedWhen(doOnSuccess = { result ->
                binding.layoutState.root.isVisible = false
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = false
                binding.rvMyList.isVisible = true
                result.payload?.let { (myList) ->
                    mylistAdapter.submitData(myList)
                }
            }, doOnLoading = {
                binding.layoutState.root.isVisible = true
                binding.layoutState.pbLoading.isVisible = true
                binding.layoutState.tvError.isVisible = false
                binding.rvMyList.isVisible = false
            }, doOnError = { err ->
                binding.layoutState.root.isVisible = true
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = true
                binding.rvMyList.isVisible = false
            }, doOnEmpty = { data ->
                binding.layoutState.root.isVisible = true
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = true
                binding.rvMyList.isVisible = false
            })
        }
    }

    private fun setClickListener() {

    }


    private fun detailMovie(movie: Movie) {
        val detailFragmentDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = FragmentDetailBinding.inflate(layoutInflater)
        val formattedRating = String.format(getString(R.string.rating_format), movie.rating)
        bottomSheetBinding.apply {
            movieTop.ivMovieImageBg.load(
                getString(
                    R.string.https_image_tmdb_org_t_p_w500,
                    movie.banner
                )
            )
            movieTop.ivMovieImage.load(
                getString(
                    R.string.https_image_tmdb_org_t_p_w500,
                    movie.image
                )
            )
            movieTop.tvMovieTitle.text = movie.title
            movieTop.tvMovieDate.text = movie.date
            movieTop.tvMovieRating.text = getString(R.string.star_icon, formattedRating)
            movieDescription.tvMovieDesc.text = movie.desc

            movieTop.shimmerLayout.isVisible = false
        }


        movieInMyList(movie, bottomSheetBinding)

        detailFragmentDialog.setContentView(bottomSheetBinding.root)
        detailFragmentDialog.show()
    }



    private fun movieInMyList(
        data: Movie,
        detailFragmentBinding: FragmentDetailBinding,
    ) {
        MyListviewModel.checkMovieList(data.id).observe(
            viewLifecycleOwner,
        ) { movieFavorite ->
            if (movieFavorite.isEmpty()) {
                detailFragmentBinding.moveDetail.btnAddToFavorite.setImageResource(R.drawable.ic_add_mylist)
               addToFavorite(data, detailFragmentBinding)
            } else {
                detailFragmentBinding.moveDetail.btnAddToFavorite.setImageResource(R.drawable.ic_done)
                clickRemoveFavorite(data.id, detailFragmentBinding)
            }
        }
    }
    private fun addToFavorite(
        data: Movie,
        detailFragmentBinding: FragmentDetailBinding,
    ) {
        detailFragmentBinding.moveDetail.btnAddToFavorite.setOnClickListener {
            addToFavorite(data)
        }
    }
    private fun addToFavorite(data: Movie) {
        MyListviewModel.addToFavorite(data).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_succes_add_to_favorite),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnError = {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_faliled_add_to_favorite),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        }
    }

    private fun clickRemoveFavorite(
        dataMovie: Int?,
        detailFragmentBinding: FragmentDetailBinding,
    ) {
        detailFragmentBinding.moveDetail.btnAddToFavorite.setOnClickListener {
            removeMovieInFavorite(dataMovie)
        }
    }

    private fun removeMovieInFavorite(movieId: Int?) {
        MyListviewModel.removeFavoriteById(movieId).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.text_succes_delete_to_favorite),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        }
    }

}