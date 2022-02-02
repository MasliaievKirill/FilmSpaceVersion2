package com.masliaiev.filmspace.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.masliaiev.filmspace.data.mapper.MovieMapper
import com.masliaiev.filmspace.domain.entity.Movie
import retrofit2.HttpException
import java.io.IOException

class TopRatedMoviesPageSource(
    private val apiService: ApiService,
    private val lang: String
) : PagingSource<Int, Movie>() {

    private val mapper = MovieMapper()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val position = params.key ?: 1

        return try {
            val response =
                apiService.getTopRatedMovies(lang = lang, page = position.toString())
            val moviesList = response.results?.map {
                mapper.mapMovieDtoToMovieEntity(it)
            }

            LoadResult.Page(
                data = moviesList!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (moviesList.isEmpty()) null else position + 1

            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int {
        return FIRST_PAGE
    }

    companion object {
        private const val FIRST_PAGE = 1
    }


}