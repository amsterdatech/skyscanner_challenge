package com.dutchtechnologies.skyscanner_challenge.model

data class Itinerary (val price:String, val agent:String, val rating:String, val legs:List<Leg>)
