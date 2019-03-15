package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.data.ItinerariesDataRepository
import com.dutchtechnologies.data.mapper.ItineraryMapper
import com.dutchtechnologies.data.mapper.SearchFormMapper
import com.dutchtechnologies.data.remote.ItineraryEntityMapper
import com.dutchtechnologies.data.remote.ItineraryRemoteImpl
import com.dutchtechnologies.data.remote.SkyscannerService
import com.dutchtechnologies.data.repository.ItineraryRemote
import com.dutchtechnologies.data.source.ItineraryDataStoreFactory
import com.dutchtechnologies.domain.ItineraryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideItineratesRepository(
        factory: ItineraryDataStoreFactory,
        mapper: ItineraryMapper,
        searchMapper: SearchFormMapper
    ): ItineraryRepository = ItinerariesDataRepository(factory, mapper, searchMapper)

    fun provideItineratesRemote(service: SkyscannerService, factory: ItineraryEntityMapper): ItineraryRemote {
        return ItineraryRemoteImpl(service, factory)
    }


}
