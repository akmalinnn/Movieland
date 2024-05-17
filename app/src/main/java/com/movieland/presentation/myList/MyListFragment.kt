package com.movieland.presentation.myList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.catnip.kokomputer.utils.proceedWhen
import com.movieland.databinding.FragmentMyListBinding
import com.movieland.presentation.common.adapter.MyListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : Fragment() {
    private lateinit var binding: FragmentMyListBinding
    private val mylistViewModel: MyListViewModel by viewModel()
    private val mylistAdapter : MyListAdapter by lazy {
        MyListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        observeData()
    }

    private fun setupList() {
        binding.rvMyList.adapter = this@MyListFragment.mylistAdapter
    }

    private fun observeData() {
        mylistViewModel.getAllFavorites().observe(viewLifecycleOwner) {
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

}