package com.dutchtechnologies.data.mapper

import com.dutchtechnologies.data.model.ItineraryEntity
import com.dutchtechnologies.domain.Itinerary
import javax.inject.Inject


open class ItineraryMapper @Inject constructor(
    private val legMapper: LegMapper
) :
    Mapper<ItineraryEntity, Itinerary> {

    override fun mapFromEntity(type: ItineraryEntity): Itinerary {
        return Itinerary(type.price, type.agent, type.rating, type.legs.map { legMapper.mapFromEntity(it) })
    }

    override fun mapToEntity(type: Itinerary): ItineraryEntity {
        return ItineraryEntity(type.price, type.agent, type.rating, type.legs.map { legMapper.mapToEntity(it) })
    }
}