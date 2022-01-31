package com.masliaiev.filmspace.di

import androidx.lifecycle.ViewModel
import com.masliaiev.filmspace.presentation.view_models.DetailActivityViewModel
import com.masliaiev.filmspace.presentation.view_models.FavouriteFragmentViewModel
import com.masliaiev.filmspace.presentation.view_models.MainFragmentViewModel
import com.masliaiev.filmspace.presentation.view_models.SearchFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(impl: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteFragmentViewModel::class)
    fun bindFavouriteFragmentViewModel(impl: FavouriteFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    fun bindSearchFragmentViewModel(impl: SearchFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    fun bindDetailActivityViewModel(impl: DetailActivityViewModel): ViewModel
}