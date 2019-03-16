package com.dutchtechnologies.skyscanner_challenge.itineraries

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.dutchtechnologies.skyscanner_challenge.R
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary
import com.dutchtechnologies.skyscanner_challenge.presentation.ItinerariesContract
import com.dutchtechnologies.skyscanner_challenge.presentation.ItinerariesPresenter
import com.dutchtechnologies.skyscanner_challenge.utils.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ItinerariesActivity : DaggerAppCompatActivity(), ItinerariesContract.View {

    @Inject
    lateinit var itineratesPresenter: ItinerariesPresenter

    private val adapter = ItinerariesAdapter()

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        itineratesPresenter.attachView(this)
        itineratesPresenter.start()
    }

    override fun onStop() {
        super.onStop()
        itineratesPresenter.stop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    private fun setupRecyclerView() {
        activity_itineraries_recycler_view.addItemDecoration(
            MarginItemDecoration(getDimens(R.dimen.spacings_eight))
        )

        activity_itineraries_recycler_view.layoutManager = LinearLayoutManager(this)
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


    override fun showProgress() {
        activity_itineraries_custom_view_loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        activity_itineraries_custom_view_loading.visibility = View.GONE
    }

    override fun showResults(itinerarios: List<Itinerary>) {
        activity_itineraries_recycler_view.visibility = View.VISIBLE
        activity_itineraries_results_filters_bar.visibility = View.VISIBLE

        adapter.items = itinerarios
    }

    override fun hideResults() {
        activity_itineraries_recycler_view.visibility = View.GONE
        activity_itineraries_results_filters_bar.visibility = View.GONE
    }

    override fun showErrorState() {

    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun setPresenter(presenter: ItinerariesContract.Presenter) {
        itineratesPresenter = presenter as ItinerariesPresenter
    }
}
