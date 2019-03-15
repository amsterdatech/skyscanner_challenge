package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.remote.PriceSearchForm
import io.reactivex.Single

interface ItineraryRemote {
    fun getItineraries(priceSearchForm: PriceSearchForm): Single<List<ItineraryEntity>>
}