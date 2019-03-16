package com.dutchtechnologies.data.source

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.remote.ItineraryRemoteImpl
import com.dutchtechnologies.data.remote.PriceSearchForm
import com.dutchtechnologies.data.repository.ItineraryDataStore
import io.reactivex.Single
import javax.inject.Inject

class ItineraryRemoteDataStore @Inject constructor(private val remote: ItineraryRemoteImpl) :
    ItineraryDataStore {

    override fun getItineraries(priceSearchForm: PriceSearchForm): Single<List<ItineraryEntity>> {
        return remote.getItineraries(priceSearchForm)
    }

}
