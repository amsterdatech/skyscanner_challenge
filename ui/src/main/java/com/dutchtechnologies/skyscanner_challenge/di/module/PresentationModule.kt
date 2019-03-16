package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.domain.interactor.GetItinerariesListSingleUseCase
import com.dutchtechnologies.skyscanner_challenge.itineraries.ItinerariesActivity
import com.dutchtechnologies.skyscanner_challenge.presentation.ItinerariesContract
import com.dutchtechnologies.skyscanner_challenge.presentation.ItinerariesPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
//    @Provides
//    fun provideItinerariesView(activity: ItinerariesActivity): ItinerariesContract.View {
//        return activity
//    }

    @Provides
    internal fun provideBrowsePresenter(getItinerates: GetItinerariesListSingleUseCase): ItinerariesPresenter {
        return ItinerariesPresenter(getItinerates)
    }
}