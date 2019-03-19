package com.dutchtechnologies.skyscanner_challenge.itineraries

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutchtechnologies.skyscanner_challenge.R
import com.dutchtechnologies.skyscanner_challenge.model.Itinerary
import com.dutchtechnologies.skyscanner_challenge.utils.*
import kotlinx.android.synthetic.main.view_holder_itinerary.view.*
import java.util.*
import kotlin.properties.Delegates


class ItinerariesAdapter : RecyclerView.Adapter<ItinerariesAdapter.ViewHolder>() {

    var items: List<Itinerary> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    lateinit var click: View.OnClickListener
    lateinit var locale: Locale
    lateinit var currency: Currency


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_itinerary,
                parent,
                false
            ), click
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], locale, currency)
        holder.containerView.setOnClickListener(click)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(var containerView: View, var onClick: View.OnClickListener? = null) :
        RecyclerView.ViewHolder(containerView) {

        private val itineraryPriceWithCarrier = containerView.custom_view_itinerary_text_view_price_with_carrier
        private val itineraryLegs = containerView.custom_view_itinerary_legs_recycler_view

        private val layoutManager = LinearLayoutManager(containerView.context, RecyclerView.VERTICAL, false)

        private val sizePrimary = containerView.context.getDimensPixelSize(R.dimen.primaryTextSize)
        private val colorSecondary = containerView.context.getColorRes(R.color.textSecondaryColor)
        private val sizeSecondary = containerView.context.getDimensPixelSize(R.dimen.secondaryTextSize)
        private val colorTertiary = containerView.context.getColorRes(R.color.textTertiaryColor)


        private val marginSize = containerView.context.getDimens(R.dimen.spacings_sixteen)

        private val legsAdapter = LegsAdapter()

        init {
            itineraryLegs.layoutManager = layoutManager
            itineraryLegs.setHasFixedSize(true)
            itineraryLegs.isNestedScrollingEnabled = false
            itineraryLegs.addItemDecoration(
                MarginItemDecoration(marginSize)
            )
            legsAdapter.click = onClick ?: View.OnClickListener { }
            itineraryLegs.adapter = legsAdapter
        }


        @SuppressLint("SetTextI18n")
        fun bind(
            itinerary: Itinerary,
            locale: Locale? = Locale("en", "GB"),
            currency: Currency? = Currency.getInstance("GBP")
        ) {
            with(itinerary) {

                val priceWithCurrency = currency?.symbol + this.price

                itineraryPriceWithCarrier?.text = TextUtils
                    .concat(
                        priceWithCurrency
                            .primaryTextBold(
                                sizePrimary, colorTertiary, true
                            ),
                        "\n",
                        "via ${this.agent}".secondaryText(
                            sizeSecondary,
                            colorSecondary, true
                        )
                    )

//                itineraryRating?.text = this.rating.primaryTextBold(
//                    sizePrimary, colorPrimary
//                )
                legsAdapter.items = this.legs
            }
        }
    }
}