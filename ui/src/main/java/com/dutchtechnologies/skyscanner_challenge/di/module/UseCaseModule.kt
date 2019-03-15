package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.data.ItinerariesDataRepository
import com.dutchtechnologies.domain.interactor.GetItinerariesListSingleUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    internal fun provideGetItinerariesListSingleUseCase(
        articlesRepository: ItinerariesDataRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetItinerariesListSingleUseCase =

        GetItinerariesListSingleUseCase(articlesRepository, ioScheduler, mainThreadScheduler)
}