package com.masliaiev.filmspace.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovieList(): LiveData<List<MovieDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(list: List<MovieDbModel>)

    @Query("SELECT * FROM favourite_movies")
    fun getFavouriteMovieList(): LiveData<List<FavouriteMovieDbModel>>

    @Query("SELECT * FROM searched_movies")
    fun getSearchedMovieList(): LiveData<List<SearchedMovieDbModel>>

    @Query("SELECT * FROM movies WHERE id=:id LIMIT 1")
    fun getMovie(id: Int): LiveData<MovieDbModel>

    @Query("SELECT * FROM favourite_movies WHERE id=:id LIMIT 1")
    fun getFavouriteMovie(id: Int): LiveData<FavouriteMovieDbModel>

    @Query("SELECT * FROM searched_movies WHERE id=:id LIMIT 1")
    fun getSearchedMovie(id: Int): LiveData<SearchedMovieDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteMovie(movie: FavouriteMovieDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearchedMovie(movie: SearchedMovieDbModel)

    @Query("DELETE FROM favourite_movies WHERE id=:id")
    suspend fun deleteFavouriteMovie(id: Int)

}