package com.dutchtechnologies.skyscanner_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = ItinerariesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_itineraries_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activity_itineraries_recycler_view.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        activity_itineraries_recycler_view.addItemDecoration(
            MarginItemDecoration(resources.getDimension(R.dimen.spacings_eight).toInt())
        )
        activity_itineraries_recycler_view.adapter = adapter


        Handler().postDelayed({
            activity_itineraries_custom_view_loading.visibility = View.GONE
            activity_itineraries_recycler_view.visibility = View.VISIBLE
            activity_itineraries_results_filters_bar.visibility = View.VISIBLE

            adapter.items = getItineraries()

        }, 1000)
    }

    fun getLegs(): List<Leg> {
        val carrierLogo = arrayOf("AA", "DL", "UA", "LH")
        val carrierName = arrayOf("American Airlines", "Delta Airlines", "United Airlines", "Lufthansa")

        var carrierLogUrl = "https://logos.skyscnr.com/images/airlines/favicon/%s.png"

        val legs = mutableListOf<Leg>()
        for (i in 1..2) {
            val carrier = (0 until carrierLogo.size - 1).random()
            val leg = Leg(
                carrierLogUrl.format(carrierLogo[carrier]),
                carrierName[carrier],
                "EDI",
                "LHR",
                "15:35",
                "17:00",
                "2h 25m",
                "Direct"
            )

            legs.add(leg)
        }

        return legs
    }

    fun getItineraries(): List<Itinerary> {
        val itineraries = mutableListOf<Itinerary>()
        for (i in 1..35) {
            val itinerary = Itinerary(
                (300..999).random().toString(),
                "Wizzair.com", "10.0",
                getLegs()
            )
            itineraries.add(itinerary)
        }

        return itineraries
    }
}
