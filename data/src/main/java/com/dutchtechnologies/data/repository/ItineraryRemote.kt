package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ItineraryEntity
import io.reactivex.Single

interface ItineraryRemote {
    fun getItineraries(): Single<List<ItineraryEntity>>
}