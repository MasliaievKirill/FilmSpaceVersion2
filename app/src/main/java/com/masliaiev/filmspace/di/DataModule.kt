package com.masliaiev.filmspace.di

import android.app.Application
import com.masliaiev.filmspace.data.database.AppDatabase
import com.masliaiev.filmspace.data.database.MovieDao
import com.masliaiev.filmspace.data.network.ApiFactory
import com.masliaiev.filmspace.data.network.ApiService
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object{

        @Provides
        @ApplicationScope
        fun provideMovieDao(application: Application): MovieDao{
            return AppDatabase.getInstance(application).movieDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}