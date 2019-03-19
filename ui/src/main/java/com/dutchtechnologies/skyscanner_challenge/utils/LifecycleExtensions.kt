package com.dutchtechnologies.skyscanner_challenge.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}


inline fun <reified T : ViewModel> AppCompatActivity.viewModelProviders(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val viewModel = viewModelProviders<T>(viewModelFactory)
    viewModel.body()
    return viewModel
}