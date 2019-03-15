package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.repository.ItineraryDataStore
import javax.inject.Inject

open class ItineraryDataStoreFactory @Inject constructor(
    private val itineraryRemoteDataStore: ItineraryRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): ItineraryDataStore {
//        if (itineraryCache.isCached() && !itineraryCache.isExpired()) {
//            return retrieveCacheDataStore()
//        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): ItineraryDataStore {
        return itineraryRemoteDataStore
    }

}
