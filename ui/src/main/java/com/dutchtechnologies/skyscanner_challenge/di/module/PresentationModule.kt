package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.domain.interactor.GetItinerariesListSingleUseCase
import com.dutchtechnologies.skyscanner_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.skyscanner_challenge.presentation.ItinerariesPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
//    @Provides
//    fun provideItinerariesView(activity: SearchResultsActivity): ItinerariesContract.View {
//        return activity
//    }

    @Provides
    internal fun provideBrowsePresenter(getItinerates: GetItinerariesListSingleUseCase, mapper:SearchRequestMapper): ItinerariesPresenter {
        return ItinerariesPresenter(getItinerates, mapper)
    }
}