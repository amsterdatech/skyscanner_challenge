package com.dutchtechnologies.data.remote

data class PriceSearchForm(
    val apiKey: String,
    var cabinClass: String,
    var country: String,
    var currency: String,
    var locale: String,
    var locationSchema: String,
    var originPlace: String,
    var destinationPlace: String,
    var outbounddate: String,
    var inbounddate: String,
    var adults: Int,
    var children: Int,
    var infants: Int
)


/*
"cabinclass=Economy&country=UK&currency=GBP&locale=en-GB&locationSchema=iata&originplace=EDI&destinationplace=LHR&outbounddate=2019-03-18&inbounddate=2019-03-19&adults=1&children=0&infants=0&apikey=ss630745725358065467897349852985&undefined="
 */

