package com.movieland.di

import android.content.SharedPreferences
import com.movieland.data.datasource.detail.DetailDataSource
import com.movieland.data.datasource.detail.DetailDataSourceImpl
import com.movieland.data.datasource.movie.MovieApiDataSource
import com.movieland.data.datasource.movie.MovieDataSource
import com.movieland.data.datasource.mylist.MyListDataSource
import com.movieland.data.datasource.mylist.MyListDataSourceImpl
import com.movieland.data.paging.NowPlayingPagingSource
import com.movieland.data.paging.PopularPagingSource
import com.movieland.data.paging.TopRatedPagingSource
import com.movieland.data.paging.UpComingPagingSource
import com.movieland.data.repository.detail.DetailMovieRepository
import com.movieland.data.repository.detail.DetailMovieRepositoryImpl
import com.movieland.data.repository.movie.MovieRepository
import com.movieland.data.repository.movie.MovieRepositoryImpl
import com.movieland.data.repository.mylist.MyListRepository
import com.movieland.data.repository.mylist.MyListRepositoryImpl
import com.movieland.data.repository.view.ViewAllPagingRepository
import com.movieland.data.repository.view.ViewAllPagingRepositoryImpl
import com.movieland.data.source.local.database.AppDatabase
import com.movieland.data.source.local.database.dao.MyListDao
import com.movieland.data.source.network.services.MovielandApiService
import com.movieland.presentation.detail.DetailViewModel
import com.movieland.presentation.home.HomeViewModel
import com.movieland.presentation.main.MainViewModel
import com.movieland.presentation.myList.MyListViewModel
import com.movieland.presentation.viewAll.ViewAllViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module


object AppModules {
    private val networkModule =
        module {
            single<MovielandApiService> { MovielandApiService.invoke() }
        }

    val localModule =
        module {
            single<AppDatabase> { AppDatabase.createInstance(androidContext()) }
            single<MyListDao> { get<AppDatabase>().cartDao() }
        }

    private val datasource =
        module {
            single<MovieDataSource> { MovieApiDataSource(get()) }
            single<DetailDataSource> { DetailDataSourceImpl(get()) }
            single<MyListDataSource> { MyListDataSourceImpl(get()) }
        }

    private val paging =
        module {
            single<NowPlayingPagingSource> { NowPlayingPagingSource(get()) }
            single<PopularPagingSource> { PopularPagingSource(get()) }
            single<TopRatedPagingSource> { TopRatedPagingSource(get()) }
            single<UpComingPagingSource> { UpComingPagingSource(get()) }
        }

    private val repository =
        module {
            single<MovieRepository> { MovieRepositoryImpl(get()) }
            single<ViewAllPagingRepository> { ViewAllPagingRepositoryImpl(get()) }
            single<DetailMovieRepository> { DetailMovieRepositoryImpl(get()) }
            single<MyListRepository> { MyListRepositoryImpl(get()) }
        }

    private val viewModel =
        module {
            viewModelOf(::HomeViewModel)
            viewModelOf(::MainViewModel)
            viewModelOf(::DetailViewModel)
            viewModelOf(::MyListViewModel)
            viewModel { params ->
                ViewAllViewModel(
                    extras = params.get(),
                    repository = get()
                )
            }
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