package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.repository.ItineraryCache
import com.dutchtechnologies.data.repository.ItineraryDataStore

open class ItineraryDataStoreFactory  constructor(
    private val itineraryCache: ItineraryCache,
    private val itineraryCacheDataStore: ItineraryCacheDataStore,
    private val itineraryRemoteDataStore: ItineraryRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): ItineraryDataStore {
        if (itineraryCache.isCached() && !itineraryCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): ItineraryDataStore {
        return itineraryCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): ItineraryDataStore {
        return itineraryRemoteDataStore
    }

}
