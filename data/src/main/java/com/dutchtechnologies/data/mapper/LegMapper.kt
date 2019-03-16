package com.dutchtechnologies.data.mapper

import com.dutchtechnologies.data.model.LegEntity
import com.dutchtechnologies.domain.Leg
import javax.inject.Inject

open class LegMapper @Inject constructor() :
    Mapper<LegEntity, Leg> {

    override fun mapFromEntity(type: LegEntity): Leg {
        return Leg(
            type.carrierLogo,
            type.carrierName,
            type.origin,
            type.destination,
            type.departure,
            type.arrival,
            type.duration,
            type.direction
        )
    }


    override fun mapToEntity(type: Leg): LegEntity {
        return LegEntity(
            type.carrierLogo,
            type.carrierName,
            type.origin,
            type.destination,
            type.departure,
            type.arrival,
            type.duration,
            type.direction
        )
    }


}