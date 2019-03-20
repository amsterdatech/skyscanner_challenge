package com.dutchtechnologies.skyscanner_challenge.presentation

import com.dutchtechnologies.domain.Itinerary
import com.dutchtechnologies.domain.interactor.SingleUseCase
import com.dutchtechnologies.domain.model.SearchRequest
import com.dutchtechnologies.skyscanner_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.skyscanner_challenge.model.Leg
import com.dutchtechnologies.skyscanner_challenge.model.SearchRequestForm
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary as ItineraryView


class ItinerariesPresenter @Inject constructor(
    private val getItinerariesUseCase: SingleUseCase<List<Itinerary>, SearchRequest>,
    private val mapper: SearchRequestMapper
) :
    ItinerariesContract.Presenter {

    lateinit var view: ItinerariesContract.View


    fun attachView(view: Any?) {
        this.view = view as ItinerariesContract.View
    }

    override fun start() {
    }

    override fun stop() {
        getItinerariesUseCase.dispose()
    }

    override fun search(searchRequest: SearchRequestForm?) {
        view.showProgress()
//        view.hideErrorState()
//        view.hideEmptyState()
//        view.hideResults()

        getItinerariesUseCase
            .execute(
                ItinerarySubscriber(), mapper.mapFromView(searchRequest)
            )
    }

    internal fun handleSuccess(results: List<Itinerary>) {
//        view.hideErrorState()
//        view.hideEmptyState()
        view.hideProgress()

        if (results.isNotEmpty()) {
            view.hideEmptyState()
            view.showResults(results.map { itinerary ->
                ItineraryView(
                    itinerary.price,
                    itinerary.agent,
                    itinerary.rating,
                    itinerary.legs.map { leg ->
                        Leg(
                            leg.carrierLogo,
                            leg.carrierName,
                            leg.origin,
                            leg.originCode,
                            leg.destination,
                            leg.destinationCode,
                            leg.departure,
                            leg.arrival,
                            leg.duration,
                            leg.direction
                        )
                    })
            })
        } else {
            view.hideProgress()
            view.hideResults()
            view.showEmptyState()
        }
    }

    inner class ItinerarySubscriber : DisposableSingleObserver<List<Itinerary>>() {

        override fun onSuccess(t: List<Itinerary>) {
            handleSuccess(t)
        }

        override fun onError(exception: Throwable) {
            view.hideProgress()
            view.hideResults()
            view.hideEmptyState()
            view.showErrorState()
        }

    }

}