package com.masliaiev.filmspace.di

import android.app.Application
import com.masliaiev.filmspace.presentation.activities.DetailActivity
import com.masliaiev.filmspace.presentation.fragments.FavouriteFragment
import com.masliaiev.filmspace.presentation.fragments.MainFragment
import com.masliaiev.filmspace.presentation.fragments.SearchFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)

    fun inject(fragment: FavouriteFragment)

    fun inject(fragment: SearchFragment)

    fun inject(activity: DetailActivity)


    @Component.Factory
    interface Factory{

        fun create(@BindsInstance application: Application):ApplicationComponent
    }
}