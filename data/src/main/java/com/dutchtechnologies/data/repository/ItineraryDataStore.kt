package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.remote.PriceSearchForm
import io.reactivex.Single

interface ItineraryDataStore {
    fun getItineraries(priceSearchFrom:PriceSearchForm): Single<List<ItineraryEntity>>
}