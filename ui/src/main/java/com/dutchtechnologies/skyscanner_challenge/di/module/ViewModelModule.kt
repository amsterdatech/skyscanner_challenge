package com.dutchtechnologies.skyscanner_challenge.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dutchtechnologies.skyscanner_challenge.di.ViewModelFactory
import com.dutchtechnologies.skyscanner_challenge.di.ViewModelKey
import com.dutchtechnologies.skyscanner_challenge.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel



}