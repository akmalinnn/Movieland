package com.movieland.data.mapper

import com.movieland.data.model.Movie
import com.movieland.data.model.MyList
import com.movieland.data.source.local.database.entity.MyListEntity

fun Movie?.toMyListEntity() =
    MyListEntity(
        id = this?.id,
        movieTitle = this?.title.orEmpty(),
        movieDate = this?.date.orEmpty(),
        movieRating = this?.rating ?: 0.0,
        movieDesc = this?.desc.orEmpty(),
        movieImage = this?.image.orEmpty(),
    )

fun MyListEntity?.toMyList() =
    Movie(
        id = this?.id?: 0,
        title = this?.movieTitle.orEmpty(),
        date = this?.movieDate.orEmpty(),
        rating = this?.movieRating ?: 0.0,
        desc = this?.movieDesc.orEmpty(),
        image = this?.movieImage.orEmpty(),
        banner = this?.movieImage.orEmpty(),
    )

fun List<MyListEntity?>.toMyLists() = this.map { it.toMyList() }