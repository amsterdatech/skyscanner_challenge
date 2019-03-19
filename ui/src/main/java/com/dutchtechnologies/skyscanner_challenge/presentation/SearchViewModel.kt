package com.dutchtechnologies.skyscanner_challenge.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dutchtechnologies.domain.interactor.GetItinerariesListSingleUseCase
import com.dutchtechnologies.skyscanner_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.skyscanner_challenge.model.Leg
import com.dutchtechnologies.skyscanner_challenge.model.SearchRequestForm
import com.dutchtechnologies.skyscanner_challenge.model.ViewData
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary as ItineraryView

class SearchViewModel @Inject constructor(
    private val getItinerariesUseCase: GetItinerariesListSingleUseCase,
    private val mapper: SearchRequestMapper

) : ViewModel() {

    private var results: MutableLiveData<ViewData<List<ItineraryView>>> = MutableLiveData()

    fun results() = results

    fun search(searchRequest: SearchRequestForm?) {
        getItinerariesUseCase
            .execute(SearchSubscriber(), mapper.mapFromView(searchRequest))

    }

    inner class SearchSubscriber : DisposableSingleObserver<List<com.dutchtechnologies.domain.Itinerary>>() {

        override fun onStart() {
            results.postValue(ViewData(ViewData.Status.LOADING))
        }

        override fun onSuccess(t: List<com.dutchtechnologies.domain.Itinerary>) {
            var success = t.map { itinerary ->
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

            results.value = ViewData(status = ViewData.Status.SUCCESS, data = success)
        }

        override fun onError(exception: Throwable) {
            results.value = ViewData(status = ViewData.Status.ERROR, error = exception)
        }

    }

    override fun onCleared() {
        getItinerariesUseCase.dispose()
        super.onCleared()
    }


}