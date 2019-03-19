package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.model.LegEntity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class ItineraryEntityMapper @Inject constructor() : EntityMapper<LivePricesResponse, List<ItineraryEntity>> {


    override fun mapFromRemote(type: LivePricesResponse): List<ItineraryEntity> {
        return type.Itineraries.map {
            processItinerary(it, type.Agents, type.Carriers, type.Legs, type.Places)
        }
    }

    private fun processItinerary(
        type: Itinerary, agents: List<Agent>,
        carriers: List<Carrier>,
        legs: List<Leg>,
        places: List<Place>
    ): ItineraryEntity {

        var agent = ""
        var price = 0.0

        var origin: String
        var destination: String
        var originCode:String
        var destinationCode:String

        var departure: String
        var arrival: String
        var duration: String
        var directionality: Int
        var carrierName: String
        var carrierLog: String

        var outLegId = type.OutboundLegId
        var inLegId = type.InboundLegId

        val pricingOptions = type.PricingOptions

        pricingOptions.forEach { po ->
            agent = agents.first { it.Id == po.Agents[0] }.Name
            price = po.Price
        }


        val legEntities = legs
            .filter {
                it.Id == outLegId || it.Id == inLegId
            }
            .map {
                origin = places.first { p -> p.Id == it.OriginStation }.Name
                originCode =  places.first { p -> p.Id == it.OriginStation }.Code
                destination = places.first { p -> p.Id == it.DestinationStation }.Name
                destinationCode = places.first { p -> p.Id == it.DestinationStation }.Code

                departure = it.Departure.formatToServerDateTimeDefaults()
                arrival = it.Arrival.formatToServerDateTimeDefaults()


                duration = "${it.Duration / 60}h ${it.Duration % 60}m"
                directionality = it.Stops.size

                val carrier = carriers.first { c -> c.Id == it.Carriers[0] }
                carrierName = carrier.Name
                carrierLog = "https://logos.skyscnr.com/images/airlines/favicon/${carrier.Code}.png"

                LegEntity(
                    carrierLog,
                    carrierName,
                    origin,
                    originCode,
                    destination,
                    destinationCode,
                    departure,
                    arrival,
                    duration,
                    directionality
                )
            }

        return ItineraryEntity(price.toString(), agent, "", legEntities)
    }

    /**
     * Pattern: yyyy-MM-dd HH:mm:ss
     */
    fun Date.formatToServerDateTimeDefaults(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(this)
    }

}