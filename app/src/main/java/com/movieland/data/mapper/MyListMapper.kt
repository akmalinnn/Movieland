package com.movieland.data.mapper

import com.movieland.data.model.MyList
import com.movieland.data.source.local.database.entity.MyListEntity

fun MyList?.toMyListEntity() =
    MyListEntity(
        id = this?.id,
        movieId = this?.movieId ?: 0,
        movieTitle = this?.movieTitle.orEmpty(),
        movieDate = this?.movieDate.orEmpty(),
        movieRating = this?.movieRating ?: 0.0,
        movieDesc = this?.movieDesc.orEmpty(),
        movieImage = this?.movieImage.orEmpty(),
        movieBool = this?.movieBool ?: false
    )

fun MyListEntity?.toMyList() =
    MyList(
        id = this?.id,
        movieId = this?.movieId ?: 0,
        movieTitle = this?.movieTitle.orEmpty(),
        movieDate = this?.movieDate.orEmpty(),
        movieRating = this?.movieRating ?: 0.0,
        movieDesc = this?.movieDesc.orEmpty(),
        movieImage = this?.movieImage.orEmpty(),
        movieBool = this?.movieBool ?: false
    )

fun List<MyListEntity?>.toMyLists() = this.map { it.toMyList() }