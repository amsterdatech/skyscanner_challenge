package com.dutchtechnologies.skyscanner_challenge.itineraries

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dutchtechnologies.skyscanner_challenge.R
import com.dutchtechnologies.skyscanner_challenge.model.Leg
import com.dutchtechnologies.skyscanner_challenge.utils.getDimensPixelSize
import com.dutchtechnologies.skyscanner_challenge.utils.getQuantityString
import kotlinx.android.synthetic.main.view_holder_leg.view.*
import com.dutchtechnologies.skyscanner_challenge.utils.load
import kotlin.properties.Delegates

class LegsAdapter : RecyclerView.Adapter<LegsAdapter.ViewHolder>() {

    var items: List<Leg> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    lateinit var click:View.OnClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_leg,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.containerView.setOnClickListener(click)

    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val legCarrierLogo = containerView.custom_view_leg_carrier_logo
        private val legDepartureArrivalTime = containerView.custom_view_leg_from_to_hours
        private val legOriginDestinationAirportsWithCarrier = containerView.custom_view_leg_from_to_and_carrier_name
        private val legDirectionality = containerView.custom_view_leg_directionality
        private val legDuration = containerView.custom_view_leg_duration


        @SuppressLint("SetTextI18n")
        fun bind(leg: Leg) {
            legCarrierLogo.load(leg.carrierLogo)

            legDepartureArrivalTime.text = "${leg.departure} - ${leg.arrival}"
            legOriginDestinationAirportsWithCarrier.text =
                "${leg.originCode} - ${leg.destinationCode}, ${leg.carrierName}"

            if (leg.direction > 0) {
                legDirectionality.text = containerView.context.getQuantityString(R.plurals.stops, leg.direction)
            } else {
                legDirectionality.text = containerView.context.getText(R.string.zero_stops)
            }


            legDuration.text = leg.duration
        }
    }
}
