package com.dutchtechnologies.data.mapper

import com.dutchtechnologies.data.remote.PriceSearchForm
import com.dutchtechnologies.domain.interactor.SearchRequest


open class SearchFormMapper : Mapper<PriceSearchForm, SearchRequest> {

    override fun mapFromEntity(type: PriceSearchForm): SearchRequest {
        return SearchRequest(
            type.apiKey,
            type.cabinClass,
            type.country,
            type.currency,
            type.locale,
            type.locationSchema,
            type.originPlace,
            type.destinationPlace,
            type.outbounddate,
            type.inbounddate,
            type.adults,
            type.children,
            type.infants
        )
    }

    override fun mapToEntity(type: SearchRequest): PriceSearchForm {
        return PriceSearchForm(
            type.apiKey,
            type.cabinClass,
            type.country,
            type.currency,
            type.locale,
            type.locationSchema,
            type.originPlace,
            type.destinationPlace,
            type.outbounddate,
            type.inbounddate,
            type.adults,
            type.children,
            type.infants
        )
    }


}