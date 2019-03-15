package com.dutchtechnologies.domain.interactor

import com.dutchtechnologies.domain.Itinerary
import com.dutchtechnologies.domain.ItineraryRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetItinerariesListSingleUseCase constructor(
    private val articlesRepository: ItineraryRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<List<Itinerary>, Unit>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: Unit?): Single<List<Itinerary>> = articlesRepository.getItineraries()

}