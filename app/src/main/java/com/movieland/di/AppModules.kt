package com.movieland.di

import com.movieland.data.repository.MovieNowPlayingRepository
import com.movieland.data.repository.MoviePopularRepository
import com.movieland.data.repository.MovieTopRatedRepository
import com.movieland.data.repository.MovieUpComingRepository
import com.movieland.data.source.network.services.MovielandApiService
import com.movieland.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


object AppModules {
    private val networkModule =
        module {
            single< MovielandApiService> { MovielandApiService.invoke() }
        }

    private val localModule =
        module {
        }

    private val datasource =
        module {

        }

    private val paging =
        module {

        }

    private val repository =
        module {

        }

    private val viewModel =
        module {
            viewModelOf(::HomeViewModel)
        }

    val modules =
        listOf<Module>(
            networkModule,
            localModule,
            datasource,
            paging,
            repository,
            viewModel,
        )
}