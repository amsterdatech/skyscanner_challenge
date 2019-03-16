package com.dutchtechnologies.skyscanner_challenge.presentation

import com.dutchtechnologies.domain.Itinerary
import com.dutchtechnologies.domain.interactor.SearchRequest
import com.dutchtechnologies.domain.interactor.SingleUseCase
import com.dutchtechnologies.skyscanner_challenge.BuildConfig
import com.dutchtechnologies.skyscanner_challenge.model.Leg
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary as ItineraryView


class ItinerariesPresenter @Inject constructor(
    private val getItinerariesUseCase: SingleUseCase<List<Itinerary>, SearchRequest>
) :
    ItinerariesContract.Presenter {

    lateinit var view: ItinerariesContract.View

    fun attachView(view: Any?) {
        this.view = view as ItinerariesContract.View
    }

    override fun start() {
        retrieveItineraries(BuildConfig.API_KEY)
    }

    override fun stop() {
        getItinerariesUseCase.dispose()
    }

    override fun retrieveItineraries(apiKey: String) {
        getItinerariesUseCase
            .execute(
                ItinerarySubscriber(),
                SearchRequest(
                    apiKey = apiKey,
                    cabinClass = "Economy",
                    country = "UK",
                    currency = "GBP",
                    locale = "GB",
                    locationSchema = "iata",
                    originPlace = "EDI",
                    destinationPlace = "LHR",
                    outbounddate = "2019-03-18",
                    inbounddate = "2019-03-19",
                    adults = 1
                )
            )
    }

    internal fun handleSuccess(results: List<Itinerary>) {
        view.hideErrorState()
        if (results.isNotEmpty()) {
            view.hideProgress()
            view.hideEmptyState()
            view.showResults(results.map { itinerary ->
                //                bufferooMapper.mapToView(it)
                ItineraryView(
                    itinerary.price,
                    itinerary.agent,
                    itinerary.rating,
                    itinerary.legs.map { leg ->
                        Leg(
                            leg.carrierLogo,
                            leg.carrierName,
                            leg.origin,
                            leg.destination,
                            leg.departure,
                            leg.arrival,
                            leg.duration,
                            leg.direction
                        )
                    })
            })
        } else {
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