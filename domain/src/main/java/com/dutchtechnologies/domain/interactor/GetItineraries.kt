package com.dutchtechnologies.domain.interactor

import com.dutchtechnologies.domain.Itinerary
import com.dutchtechnologies.domain.ItineraryRepository
import io.reactivex.Scheduler

class GetItinerariesListSingleUseCase constructor(
    private val repository: ItineraryRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<List<Itinerary>, SearchRequest>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: SearchRequest?) = repository.getItineraries(params)

}