package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.skyscanner_challenge.itineraries.ItinerariesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeItinerariesActivity(): ItinerariesActivity
}