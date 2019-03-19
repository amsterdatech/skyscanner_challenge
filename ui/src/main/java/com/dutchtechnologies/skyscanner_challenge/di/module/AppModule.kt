package com.dutchtechnologies.skyscanner_challenge.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, UseCaseModule::class])
abstract class AppModule {
    @Binds
    abstract fun provideApp(application: Application): Context

}