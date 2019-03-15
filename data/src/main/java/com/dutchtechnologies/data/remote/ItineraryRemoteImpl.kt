package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.repository.ItineraryRemote
import io.reactivex.Single

class ItineraryRemoteImpl constructor(
    private val service: SkyscannerService,
    private val entityMapper: ItineraryEntityMapper
) :
    ItineraryRemote {

    override fun getItineraries(priceSearchForm: PriceSearchForm): Single<List<ItineraryEntity>> {
        return service
            .createSession(priceSearchForm)
            .map {
                return@map it.headers()["Location"] ?: ""
            }
            .flatMap { sessionId ->
                if (sessionId.isEmpty()) {
                    return@flatMap Single.defer {
                        Single.just(mutableListOf<ItineraryEntity>())
                    }
                }

                return@flatMap service
                    .getLivePrices(sessionId, "")
                    .map {
                        entityMapper.mapFromRemote(it.livePrices)
                    }
            }


    }
}