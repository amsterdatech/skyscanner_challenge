package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.repository.ItineraryCache
import com.dutchtechnologies.data.repository.ItineraryDataStore
import io.reactivex.Single

class ItineraryCacheDataStore constructor(private val cache: ItineraryCache) :
    ItineraryDataStore {

    override fun getItineraries(): Single<List<ItineraryEntity>> {
        return cache.getBufferoos()
    }
}
