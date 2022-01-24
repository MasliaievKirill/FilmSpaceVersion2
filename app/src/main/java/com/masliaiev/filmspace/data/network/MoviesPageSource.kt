package com.masliaiev.filmspace.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.masliaiev.filmspace.data.mapper.MovieMapper
import com.masliaiev.filmspace.domain.entity.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviesPageSource(
    private val apiService: ApiService,
    private val lang: String,
    private val sorted: String
) : PagingSource<Int, Movie>() {

    private val mapper = MovieMapper()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val position = params.key ?: 1

        return try {
            val response =
                apiService.getMovies(lang = lang, sorted = sorted, page = position.toString())
            val moviesListDto = response.results?.map {
                mapper.mapMovieDtoToMovieEntity(it)
            }

            LoadResult.Page(
                data = moviesListDto!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (moviesListDto.isEmpty()) null else position + 1

            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }


}