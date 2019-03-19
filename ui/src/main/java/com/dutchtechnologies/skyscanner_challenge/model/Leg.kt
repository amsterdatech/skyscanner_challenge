package com.dutchtechnologies.skyscanner_challenge.model

data class Leg (val carrierLogo:String,
                val carrierName:String,
                val origin:String,
                val originCode:String,
                val destination:String,
                val destinationCode:String,
                val departure:String,
                val arrival:String,
                val duration:String,
                val direction:Int)
