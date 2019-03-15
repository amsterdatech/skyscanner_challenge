package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.data.remote.SkyscannerService
import com.dutchtechnologies.skyscanner_challenge.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val DATE_FORMAT = "dd-MM-yyyy'T'HH:mm:ss"
        private const val NAMED_REST_SERVER_URL = "NAMED_REST_SERVER_URL"

    }

    @Provides
    @Singleton
    @Named(NAMED_REST_SERVER_URL)
    fun provideRestServer() = ""//        BuildConfig.REST_SERVER

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat(DATE_FORMAT)
        .disableHtmlEscaping()
        .create()

    @Provides
    @Singleton
    fun provideGSONConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )


    @Provides
    @Singleton
    fun provideRetrofitRest(
        @Named(NAMED_REST_SERVER_URL) serverUrl: String, httpLoggingInterceptor: HttpLoggingInterceptor,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(serverUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gsonConverterFactory)
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor).connectTimeout(timeout(), TimeUnit.SECONDS)
                .readTimeout(timeout(), TimeUnit.SECONDS)
                .writeTimeout(timeout(), TimeUnit.SECONDS)
                .build()
        )
        .build()

    private fun timeout(): Long = 30

    @Provides
    @Singleton
    fun provideSkyscannerApi(retrofit: Retrofit): SkyscannerService = retrofit.create(SkyscannerService::class.java)

}