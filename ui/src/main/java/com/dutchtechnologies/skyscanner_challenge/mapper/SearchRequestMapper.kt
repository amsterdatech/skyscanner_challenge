package com.dutchtechnologies.skyscanner_challenge.mapper

import com.dutchtechnologies.domain.model.SearchRequest
import com.dutchtechnologies.skyscanner_challenge.model.SearchRequestForm
import javax.inject.Inject

open class SearchRequestMapper @Inject constructor() : Mapper<SearchRequestForm?, SearchRequest> {


    override fun mapToView(type: SearchRequest): SearchRequestForm {
        return SearchRequestForm()
    }

    override fun mapFromView(type: SearchRequestForm?): SearchRequest {
        type?.let {
            return SearchRequest(
                apiKey = type.apiKey,
                cabinClass = type.cabinClass,
                country = type.country,
                currency = type.currency,
                locale = type.locale,
                locationSchema = type.locationSchema,
                originPlace = type.originPlace,
                destinationPlace = type.destinationPlace,
                outbounddate = type.outbounddate,
                inbounddate = type.inbounddate,
                adults = type.adults,
                children = type.children,
                infants = type.infants,
                pageIndex = type.pageIndex
            )
        }

        return SearchRequest()
    }
}


