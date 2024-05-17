package com.movieland.data.repository.mylist

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceed
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.datasource.mylist.MyListDataSource
import com.movieland.data.mapper.toMyListEntity
import com.movieland.data.mapper.toMyLists
import com.movieland.data.model.Movie
import com.movieland.data.model.MovieDetail
import com.movieland.data.model.MyList
import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.lang.IllegalStateException

class MyListRepositoryImpl(
    private val dataSource: MyListDataSource
) : MyListRepository {

    override fun getAllMyList(): Flow<ResultWrapper<Pair<List<Movie>, Double>>> {
        return dataSource.getAllMyList()
            .map {
                // mapping into Favorite list and sum the total price
                proceed {
                    val result = it.toMyLists()
                    val totalPrice = result.sumOf { it.rating }
                    Pair(result, totalPrice)
                }
            }.map {
                // map to check when list is empty
                if (it.payload?.first?.isEmpty() == false) return@map it
                ResultWrapper.Empty(it.payload)
            }.onStart {
                emit(ResultWrapper.Loading())
                delay(1000)
            }
    }

    override fun createMyList(item: Movie): Flow<ResultWrapper<Boolean>> {
        return item.id?.let { itemId ->
            // when id is not null
            proceedFlow {
                val affectedRow =
                    dataSource.insertMyList(
                        MyListEntity(
                            id = itemId,
                            movieTitle = item.title,
                            movieImage = item.image,
                            movieRating = item.rating,
                            movieDesc = item.desc,
                            movieDate = item.date,
                        ),
                    )
                delay(2000)
                affectedRow > 0
            }
        } ?: flow {
            emit(ResultWrapper.Error(IllegalStateException("catalog ID not found")))
        }
    }

    override fun checkFavoriteById(movieId: Int?): Flow<List<MyListEntity>> {
        return dataSource.checkFavoriteById(movieId)
    }


    override fun deleteMyList(item: Movie): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.deleteMyList(item.toMyListEntity()) > 0 }
    }

    override fun deleteMyListById(movieId: Int?): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.deleteMyListbyId(movieId) > 0 }
    }

    override fun deleteAll(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataSource.deleteAll()
            true
        }
    }
}