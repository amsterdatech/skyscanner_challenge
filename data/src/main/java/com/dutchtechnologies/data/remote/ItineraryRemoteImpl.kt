package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.repository.ItineraryRemote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import javax.inject.Inject

class ItineraryRemoteImpl @Inject constructor(
    private val gson: Gson,
    private val service: SkyscannerService,
    private val entityMapper: ItineraryEntityMapper
) :
    ItineraryRemote {

    override fun getItineraries(priceSearchForm: PriceSearchForm): Single<List<ItineraryEntity>> {
        val params: Map<String, String> = gson.fromJson(gson.toJson(priceSearchForm))

        return service
            .createSession(params)
            .map {
                return@map it.headers()["Location"] ?: ""
                //If priceForm is almost empty, server returns 404, should throw error or handle it in a special case
            }
            .flatMap { location ->
                if (location.isEmpty()) {
                    return@flatMap Single.defer {
                        Single.just(mutableListOf<ItineraryEntity>())
                    }
                }

                val sessionId = location.substring(location.lastIndexOf("/") + 1, location.length)

                return@flatMap service
                    .getLivePrices(
                        sessionId = sessionId,
                        apiKey = priceSearchForm.apiKey,
                        pageIndex = priceSearchForm.pageIndex
                    )
                    .map {
                        entityMapper.mapFromRemote(it)
                    }
            }
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}