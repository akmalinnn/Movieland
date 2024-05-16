package com.movieland.di

import com.movieland.data.datasource.movie.MovieApiDataSource
import com.movieland.data.datasource.movie.MovieDataSource
import com.movieland.data.repository.movie.MovieRepository
import com.movieland.data.repository.movie.MovieRepositoryImpl
import com.movieland.data.source.network.services.MovielandApiService
import com.movieland.presentation.home.HomeViewModel
import com.movieland.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


object AppModules {
    private val networkModule =
        module {
            single<MovielandApiService> { MovielandApiService.invoke() }
        }

    private val localModule =
        module {
        }

    private val datasource =
        module {
            single<MovieDataSource> { MovieApiDataSource(get()) }

        }

    private val paging =
        module {

        }

    private val repository =
        module {
            single<MovieRepository> { MovieRepositoryImpl(get()) }
        }

    private val viewModel =
        module {
            viewModelOf(::HomeViewModel)
            viewModelOf(::MainViewModel)
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