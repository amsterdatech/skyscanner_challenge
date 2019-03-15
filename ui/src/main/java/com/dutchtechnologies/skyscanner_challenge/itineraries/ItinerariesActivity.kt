package com.dutchtechnologies.skyscanner_challenge.itineraries

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutchtechnologies.skyscanner_challenge.R
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary
import com.dutchtechnologies.skyscanner_challenge.model.Leg
import com.dutchtechnologies.skyscanner_challenge.utils.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ItinerariesActivity : DaggerAppCompatActivity() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private val adapter = ItinerariesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()

        Handler().postDelayed({
            activity_itineraries_custom_view_loading.visibility = View.GONE
            activity_itineraries_recycler_view.visibility = View.VISIBLE
            activity_itineraries_results_filters_bar.visibility = View.VISIBLE

            adapter.items = getItineraries()

        }, 1000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    private fun setupRecyclerView() {
        activity_itineraries_recycler_view.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        activity_itineraries_recycler_view.addItemDecoration(
            MarginItemDecoration(getDimens(R.dimen.spacings_eight).toInt())
        )
        activity_itineraries_recycler_view.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(activity_itineraries_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val titleToolbar = "BUD - London"
        val subtitleToolbar = "12 Nov - 16 Nov, 1 adult, economy"

        activity_itineraries_text_view_query_info.text =
            TextUtils.concat(
                titleToolbar.primaryTextBold(
                    getDimensPixelSize(R.dimen.toolbarPrimaryTextSize),
                    getColorRes(android.R.color.white)
                ), "\n",
                subtitleToolbar.secondaryText(
                    getDimensPixelSize(R.dimen.secondaryTextSize),
                    getColorRes(R.color.subtitleSecondaryColor)
                )
            )
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
