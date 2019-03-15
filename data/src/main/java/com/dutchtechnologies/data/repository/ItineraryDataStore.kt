package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ItineraryEntity
import io.reactivex.Single

interface ItineraryDataStore {


    fun getItineraries(): Single<List<ItineraryEntity>>

}