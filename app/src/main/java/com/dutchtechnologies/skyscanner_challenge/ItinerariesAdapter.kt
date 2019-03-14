package com.dutchtechnologies.skyscanner_challenge

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.Layout
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_itinerary.view.*
import kotlin.properties.Delegates


class ItinerariesAdapter : RecyclerView.Adapter<ItinerariesAdapter.ViewHolder>() {

    var items: List<Itinerary> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_itinerary, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val itineraryPriceWithCarrier = containerView.custom_view_itinerary_text_view_price_with_carrier
        private val itineraryRating = containerView.custom_view_itinerary_text_view_rating
        private val itineraryLegs = containerView.custom_view_itinerary_legs_recycler_view

        private val layoutManager = LinearLayoutManager(containerView.context, RecyclerView.VERTICAL, false)

        private val textColorPrimary =
            ResourcesCompat.getColor(containerView.context.resources, R.color.textPrimaryColor, null)
        private val textColorSecondary =
            ResourcesCompat.getColor(containerView.context.resources, R.color.textSecondaryColor, null)

        private val textPrimarySize = containerView.context.resources.getDimensionPixelSize(R.dimen.primaryTextSize)
        private val textSecondSize = containerView.context.resources.getDimensionPixelSize(R.dimen.secondaryTextSize)
        private val legsAdapter = LegsAdapter()

        init {
            itineraryLegs.layoutManager = layoutManager
            itineraryLegs.setHasFixedSize(true)
            itineraryLegs.isNestedScrollingEnabled = false
            itineraryLegs.adapter = legsAdapter
        }


        @SuppressLint("SetTextI18n")
        fun bind(itinerary: Itinerary) {
            with(itinerary) {
                val price = "${this.price}"
                val span1 = SpannableString(price)
//                ss1.setSpan(RelativeSizeSpan(0.3f), 3, this.agent.length - 1, 0) // set size
                span1.setSpan(AbsoluteSizeSpan(textPrimarySize), 0, price.length, SPAN_INCLUSIVE_INCLUSIVE)
                span1.setSpan(ForegroundColorSpan(textColorPrimary), 0, price.length, 0)
                span1.setSpan(StyleSpan(Typeface.BOLD), 0, price.length, 0)// set color
                span1.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0, price.length, 0)


                val agentName = "via ${this.agent}"
                val span2 = SpannableString(agentName)
                span2.setSpan(AbsoluteSizeSpan(textSecondSize), 0, agentName.length, SPAN_INCLUSIVE_INCLUSIVE)
                span2.setSpan(ForegroundColorSpan(textColorSecondary), 0, agentName.length, 0)// set color

                val result = TextUtils.concat(span1, "\n", span2)

                itineraryPriceWithCarrier?.text = result
                itineraryRating?.text = this.rating

                itinerary.legs.let {
                    legsAdapter.items = it
                }
            }
        }
    }
}