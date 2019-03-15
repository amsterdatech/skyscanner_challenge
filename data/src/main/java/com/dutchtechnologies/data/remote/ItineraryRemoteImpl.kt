package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.repository.ItineraryRemote
import io.reactivex.Single

class ItineraryRemoteImpl constructor(
    private val service: SkyscannerService,
    private val entityMapper: ItineraryEntityMapper
) :
    ItineraryRemote {

    /**
     * Retrieve a list of [BufferooEntity] instances from the [BufferooService].
     */
    override fun getItineraries(): Single<List<ItineraryEntity>> {
        return service.getLivePrices(" ", "")
            .map {
                entityMapper.mapFromRemote(it.livePrices)
            }
    }
}