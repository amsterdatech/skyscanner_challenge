package com.dutchtechnologies.skyscanner_challenge

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_leg.view.*
import load
import kotlin.properties.Delegates

class LegsAdapter : RecyclerView.Adapter<LegsAdapter.ViewHolder>() {

        var items: List<Leg> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_leg, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val legCarrierLogo = containerView.custom_view_leg_carrier_logo
        private val legDepartureArrivalTime = containerView.custom_view_leg_from_to_hours
        private val legOriginDestinationAirportsWithCarrier = containerView.custom_view_leg_from_to_and_carrier_name
        private val legDirectionality = containerView.custom_view_leg_directionality
        private val legDuration = containerView.custom_view_leg_duration

        @SuppressLint("SetTextI18n")
        fun bind(leg: Leg) {
            legDepartureArrivalTime.text = "${leg.departure} - ${leg.arrival}"
            legOriginDestinationAirportsWithCarrier.text = "${leg.origin} - ${leg.destination}, ${leg.carrierName}"
            legDirectionality.text = leg.direction
            legDuration.text = leg.duration
            legCarrierLogo.load(leg.carrierLogo)
        }
    }
}
