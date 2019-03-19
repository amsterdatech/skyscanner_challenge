package com.dutchtechnologies.skyscanner_challenge.di

import android.app.Application
import com.dutchtechnologies.skyscanner_challenge.di.module.ActivityBuilder
import com.dutchtechnologies.skyscanner_challenge.di.module.AppModule
import com.dutchtechnologies.skyscanner_challenge.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}