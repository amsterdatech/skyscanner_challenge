package com.dutchtechnologies.data

import com.dutchtechnologies.data.mapper.ItineraryMapper
import com.dutchtechnologies.data.source.ItineraryDataStoreFactory
import com.dutchtechnologies.domain.Itinerary
import com.dutchtechnologies.domain.ItineraryRepository
import io.reactivex.Single

/**
 * Provides an implementation of the [ItineraryRepository] interface for communicating to and from
 * data sources
 */
class ItinerariesDataRepository constructor(
    private val factory: ItineraryDataStoreFactory,
    private val mapper: ItineraryMapper
) :
    ItineraryRepository {


    override fun getItineraries(): Single<List<Itinerary>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore
            .getItineraries().map { list ->
                list.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

}
