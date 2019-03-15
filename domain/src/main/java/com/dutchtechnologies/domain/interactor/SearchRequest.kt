package com.dutchtechnologies.domain.interactor

data class SearchRequest(
    val apiKey: String = "",
    val cabinClass: String = "",
    val country: String = "",
    val currency: String = "",
    val locale: String = "",
    val locationSchema: String = "",
    val originPlace: String = "",
    val destinationPlace: String = "",
    val outbounddate: String = "",
    val inbounddate: String = "",
    val adults: Int = 0,
    val children: Int = 0,
    val infants: Int = 0
)