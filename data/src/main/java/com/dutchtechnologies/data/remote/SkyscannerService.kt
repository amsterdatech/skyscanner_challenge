package com.dutchtechnologies.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query
import java.util.*

interface SkyscannerService {

    @GET("pricing/uk1/v1.0/{sessionId}")
    fun getLivePrices(@Path("sessionId") sessionId:String, @Query("apiKey")apiKey:String): Single<LivePricesResponse>

    @POST("pricing/v1.0")
    @FormUrlEncoded
    fun createSession(@FieldMap(encoded = true) params:Map<String,String>):Single<Response<Any>>

}