package com.dutchtechnologies.skyscanner_challenge.presentation

import com.dutchtechnologies.domain.model.SearchRequest
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary
import com.dutchtechnologies.skyscanner_challenge.model.SearchRequestForm

interface ItinerariesContract {

    interface View : BaseView<Presenter> {
        fun showResults(results: List<Itinerary>)
        fun showResultLoading()
        fun hideResultLoading()
    }


    interface BasePresenter {
        fun start()

        fun stop()
    }

    interface BaseView<in T : BasePresenter> {
        fun setPresenter(presenter: T)

        fun showProgress()

        fun hideProgress()

        fun hideResults()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {
        fun search(searchRequest: SearchRequestForm? = null)
    }


}