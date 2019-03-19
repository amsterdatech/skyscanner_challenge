package com.dutchtechnologies.skyscanner_challenge.itineraries

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dutchtechnologies.skyscanner_challenge.BuildConfig
import com.dutchtechnologies.skyscanner_challenge.R
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary
import com.dutchtechnologies.skyscanner_challenge.model.SearchRequestForm
import com.dutchtechnologies.skyscanner_challenge.model.ViewData
import com.dutchtechnologies.skyscanner_challenge.presentation.ItinerariesContract
import com.dutchtechnologies.skyscanner_challenge.presentation.SearchViewModel
import com.dutchtechnologies.skyscanner_challenge.utils.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search_results.*
import java.util.*
import javax.inject.Inject

class SearchResultsActivity : DaggerAppCompatActivity(), ItinerariesContract.View, View.OnClickListener, LifecycleOwner {

//    @Inject
//    lateinit var itineratesPresenter: ItinerariesPresenter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var searchViewModel: SearchViewModel


    private var searchRequestForm: SearchRequestForm? = null

    private val adapter = ItinerariesAdapter()

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        private const val SEARCH_REQUEST_FORM = "SEARCH_REQUEST_FORM"

        @JvmStatic
        private val DEFAULT_SEARCH = SearchRequestForm(
            apiKey = BuildConfig.API_KEY,
            cabinClass = "Economy",
            country = "UK",
            currency = "GBP",
            locale = "GB",
            locationSchema = "iata",
            originPlace = "EDI",
            destinationPlace = "LON",
            outbounddate = "2019-03-25",
            inbounddate = "2019-03-26",
            adults = 1
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = withViewModel(viewModelFactory) {
            observeResults(searchViewModel)
        }


        savedInstanceState?.let {
            if (it.containsKey(SEARCH_REQUEST_FORM)) {
                searchRequestForm = it.getParcelable(SEARCH_REQUEST_FORM)
            }
        } ?: run {
            searchRequestForm = extra(SEARCH_REQUEST_FORM, SearchResultsActivity.DEFAULT_SEARCH)
        }

        setContentView(R.layout.activity_search_results)
        setupToolbar()
        setupRecyclerView()


    }

    private fun observeResults(searchViewModel: SearchViewModel) {
        observe(searchViewModel.results()) {
            when (it?.status) {
                ViewData.Status.LOADING -> {
                    showProgress()
                }

                ViewData.Status.SUCCESS -> {
                    hideProgress()
                    hideErrorState()

                    it.data?.let { list ->
                        if (!list.isEmpty()) {
                            hideEmptyState()
                            showResults(list)

                        } else {
                            hideResults()
                            showEmptyState()
                        }
                    }
                }

                ViewData.Status.ERROR -> {
                    hideProgress()
                    hideEmptyState()
                    hideResults()
                    showErrorState()

                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
//        itineratesPresenter.attachView(this)
//        itineratesPresenter.start()
//        itineratesPresenter.search(searchRequestForm)
        searchViewModel.search(searchRequestForm)


    }

    override fun onStop() {
        super.onStop()
//        itineratesPresenter.stop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(SEARCH_REQUEST_FORM, searchRequestForm)
        super.onSaveInstanceState(outState)
    }


    private fun setupRecyclerView() {
        activity_itineraries_recycler_view.addItemDecoration(
            MarginItemDecoration(getDimens(R.dimen.spacings_eight))
        )
        activity_itineraries_recycler_view.layoutManager = LinearLayoutManager(this)

        adapter.click = this

        searchRequestForm?.let {

            val locale = it.locale.split("-")
            val lang = locale[0]
            var country = ""

            if (locale.size > 1) {
                locale[1]
            }

            adapter.locale = Locale(lang, country)
            adapter.currency = Currency.getInstance(it.currency)
        }
        activity_itineraries_recycler_view.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(activity_itineraries_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val titleToolbar = "${searchRequestForm?.originPlace} - ${searchRequestForm?.destinationPlace}"

        val inboundDate = searchRequestForm?.inbounddate?.parseIsoDateFormat()?.formatToDayMonthName()
        val outboundDate = searchRequestForm?.outbounddate?.parseIsoDateFormat()?.formatToDayMonthName()

        var adults = getQuantityString(R.plurals.adults, searchRequestForm?.adults ?: 0)
        val cabineClass = searchRequestForm?.cabinClass

        val subtitleToolbar = "$outboundDate - $inboundDate"

        activity_itineraries_text_view_query_info.text =
            TextUtils.concat(
                titleToolbar.primaryTextBold(
                    getDimensPixelSize(R.dimen.toolbarPrimaryTextSize),
                    getColorRes(android.R.color.white)
                ), "\n",
                subtitleToolbar.secondaryText(
                    getDimensPixelSize(R.dimen.toolbarSecondaryTextSize),
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

    override fun showResults(results: List<Itinerary>) {
        activity_itineraries_recycler_view.visibility = View.VISIBLE
        activity_itineraries_results_filters_bar.visibility = View.VISIBLE
        activity_itineraries_text_view_count_pages_results.text = "${results.size} results"

        adapter.items = results
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

//    override fun setPresenter(presenter: ItinerariesContract.Presenter) {
//        itineratesPresenter = presenter as ItinerariesPresenter
//    }

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }
}
