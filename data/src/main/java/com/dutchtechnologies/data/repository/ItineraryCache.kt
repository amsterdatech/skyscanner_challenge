package com.dutchtechnologies.data.repository

import com.dutchtechnologies.data.model.ItineraryEntity
import io.reactivex.Single

interface ItineraryCache {

    fun getBufferoos(): Single<List<ItineraryEntity>>

    fun isCached(): Boolean

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

}
