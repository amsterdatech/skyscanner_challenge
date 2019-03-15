package com.dutchtechnologies.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SkyscannerService {

    @GET("pricing/uk1/v1.0/{sessionId}")

    fun getLivePrices(@Path("sessionId") sessionId:String, @Query("apiKey")apiKey:String): Single<LivePricesResponse>

    class LivePricesResponse {
        lateinit var livePrices: ItineraryModel
    }

}