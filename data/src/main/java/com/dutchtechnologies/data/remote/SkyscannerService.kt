package com.dutchtechnologies.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface SkyscannerService {

    @GET("pricing/uk1/v1.0/{sessionId}")
    fun getLivePrices(@Path("sessionId") sessionId:String, @Query("apiKey")apiKey:String): Single<LivePricesResponse>


    @POST("pricing/v1.0")
    fun createSession(@Body priceSearchForm:PriceSearchForm):Single<Response<Any>>


    class LivePricesResponse {
        lateinit var livePrices: ItineraryModel
    }

}