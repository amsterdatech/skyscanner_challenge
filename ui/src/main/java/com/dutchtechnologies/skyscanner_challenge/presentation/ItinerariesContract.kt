package com.dutchtechnologies.skyscanner_challenge.presentation

import com.dutchtechnologies.skyscanner_challenge.model.Itinerary

interface ItinerariesContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showResults(itinerarios: List<Itinerary>)

        fun hideResults()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface BasePresenter {
        fun start()

        fun stop()
    }


    interface Presenter : BasePresenter {
        fun retrieveItineraries(apiKey:String)
    }

    interface BaseView<in T : BasePresenter> {
        fun setPresenter(presenter: T)

    }
}