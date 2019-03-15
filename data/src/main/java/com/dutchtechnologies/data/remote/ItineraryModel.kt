package com.dutchtechnologies.data.remote

import java.util.*


class ItineraryModel(
    val Agents: List<Agent>,
    val Carriers: List<Carrier>,
    val Currencies: List<Currency>,
    val Itineraries: List<Itinerary>,
    val Legs: List<Leg>,
    val Places: List<Place>,
    val Query: Query,
    val Segments: List<Segment>,
    val ServiceQuery: ServiceQuery,
    val SessionKey: String,
    val Status: String
)

data class Carrier(
    val Code: String,
    val DisplayCode: String,
    val Id: Int,
    val ImageUrl: String,
    val Name: String
)

data class ServiceQuery(
    val DateTime: String
)

data class Itinerary(
    val BookingDetailsLink: BookingDetailsLink,
    val InboundLegId: String,
    val OutboundLegId: String,
    val PricingOptions: List<PricingOption>
)

data class BookingDetailsLink(
    val Body: String,
    val Method: String,
    val Uri: String
)

data class PricingOption(
    val Agents: List<Int>,
    val DeeplinkUrl: String,
    val Price: Double,
    val QuoteAgeInMinutes: Int
)

data class Leg(
    val Arrival: Date,
    val Carriers: List<Int>,
    val Departure: Date,
    val DestinationStation: Int,
    val Directionality: String,
    val Duration: Int,
    val FlightNumbers: List<FlightNumber>,
    val Id: String,
    val JourneyMode: String,
    val OperatingCarriers: List<Int>,
    val OriginStation: Int,
    val SegmentIds: List<Int>,
    val Stops: List<Int>
)

data class FlightNumber(
    val CarrierId: Int,
    val FlightNumber: String
)

data class Place(
    val Code: String,
    val Id: Int,
    val Name: String,
    val ParentId: Int,
    val Type: String
)

data class Agent(
    val Id: Int,
    val ImageUrl: String,
    val Name: String,
    val OptimisedForMobile: Boolean,
    val Status: String,
    val Type: String
)

data class Query(
    val Adults: Int,
    val CabinClass: String,
    val Children: Int,
    val Country: String,
    val Currency: String,
    val DestinationPlace: String,
    val GroupPricing: Boolean,
    val InboundDate: Date,
    val Infants: Int,
    val Locale: String,
    val LocationSchema: String,
    val OriginPlace: String,
    val OutboundDate: Date
)

data class Segment(
    val ArrivalDateTime: Date,
    val Carrier: Int,
    val DepartureDateTime: Date,
    val DestinationStation: Int,
    val Directionality: String,
    val Duration: Int,
    val FlightNumber: String,
    val Id: Int,
    val JourneyMode: String,
    val OperatingCarrier: Int,
    val OriginStation: Int
)

data class Currency(
    val Code: String,
    val DecimalDigits: Int,
    val DecimalSeparator: String,
    val RoundingCoefficient: Int,
    val SpaceBetweenAmountAndSymbol: Boolean,
    val Symbol: String,
    val SymbolOnLeft: Boolean,
    val ThousandsSeparator: String
)
