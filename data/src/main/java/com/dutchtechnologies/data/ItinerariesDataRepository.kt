package com.dutchtechnologies.data

import com.dutchtechnologies.data.mapper.ItineraryMapper
import com.dutchtechnologies.data.mapper.SearchFormMapper
import com.dutchtechnologies.data.source.ItineraryDataStoreFactory
import com.dutchtechnologies.domain.Itinerary
import com.dutchtechnologies.domain.ItineraryRepository
import com.dutchtechnologies.domain.model.SearchRequest
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [ItineraryRepository] interface for communicating to and from
 * data sources
 */
class ItinerariesDataRepository @Inject constructor(
    private val factory: ItineraryDataStoreFactory,
    private val mapper: ItineraryMapper,
    private val searchMapper: SearchFormMapper
) :
    ItineraryRepository {


    override fun getItineraries(search: SearchRequest?): Single<List<Itinerary>> {

        val dataStore = factory.retrieveDataStore()
        return dataStore
            .getItineraries(
                searchMapper
                    .mapToEntity(search ?: SearchRequest())
            )
            .map { list ->
                list.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

}
