package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.data.model.LegEntity
import java.text.SimpleDateFormat
import java.util.*

open class ItineraryEntityMapper constructor() : EntityMapper<ItineraryModel, List<ItineraryEntity>> {


    override fun mapFromRemote(type: ItineraryModel): List<ItineraryEntity> {
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
        var departure: String
        var arrival: String
        var duration: Int
        var directionality: String
        var carrierName: String
        var carrierLog: String

        var outLegId = type.OutboundLegId
        var inLegId = type.InboundLegId

        val pricingOptions = type.PricingOptions

        pricingOptions.forEach { po ->
            agent = agents.first { it.Id == po.Agents[0] }.Name
            price = po.Price
        }

        val legs = legs.filter {
            it.Id == outLegId || it.Id == inLegId
        }

        val legEntities = legs.map {
            origin = places.first { p -> p.Id == it.OriginStation }.Name
            destination = places.first { p -> p.Id == it.DestinationStation }.Name
            departure = it.Departure.formatToServerDateTimeDefaults()
            arrival = it.Arrival.formatToServerDateTimeDefaults()
            duration = it.Duration
            directionality = it.Directionality

            val carrier = carriers.first { c -> c.Id == it.Carriers[0] }
            carrierName = carrier.Name
            carrierLog = carrier.ImageUrl

            LegEntity(
                carrierLog,
                carrierName,
                origin,
                destination,
                departure,
                arrival,
                duration.toString(),
                directionality
            )
        }

        return ItineraryEntity(price.toString(), agent, "", legEntities)
    }

    /**
     * Pattern: yyyy-MM-dd HH:mm:ss
     */
    fun Date.formatToServerDateTimeDefaults(): String{
        val sdf= SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(this)
    }

}