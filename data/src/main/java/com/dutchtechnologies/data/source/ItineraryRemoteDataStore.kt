package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.remote.PriceSearchForm
import com.dutchtechnologies.data.repository.ItineraryDataStore
import com.dutchtechnologies.data.repository.ItineraryRemote
import io.reactivex.Single

class ItineraryRemoteDataStore constructor(private val remote: ItineraryRemote) :
    ItineraryDataStore {

    override fun getItineraries(priceSearchForm: PriceSearchForm): Single<List<ItineraryEntity>> {
        return remote.getItineraries(priceSearchForm)
    }

}
