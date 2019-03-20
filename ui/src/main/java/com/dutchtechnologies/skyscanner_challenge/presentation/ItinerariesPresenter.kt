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

        searchRequest?.pageIndex?.let {
            if(it == 1){
                view.showProgress()
            }else{
                view.showResultLoading()
            }
        }

        getItinerariesUseCase
            .execute(
                ItinerarySubscriber(), mapper.mapFromView(searchRequest)
            )
    }

    internal fun handleSuccess(results: List<Itinerary>) {
        resetViewState()

        if (results.isNotEmpty()) {
            view.showResults(mapFromDomainToView(results))

        } else {
//            view.hideResults()
            view.showEmptyState()
        }
    }

    private fun mapFromDomainToView(results: List<Itinerary>): List<com.dutchtechnologies.skyscanner_challenge.model.Itinerary> {
        return results.map { itinerary ->
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
        }
    }

    private fun resetViewState() {
        view.hideErrorState()
        view.hideEmptyState()
        view.hideProgress()
        view.hideResultLoading()
    }

    inner class ItinerarySubscriber : DisposableSingleObserver<List<Itinerary>>() {

        override fun onSuccess(t: List<Itinerary>) {
            handleSuccess(t)
        }

        override fun onError(exception: Throwable) {
            resetViewState()
            view.showErrorState()
        }

    }

}