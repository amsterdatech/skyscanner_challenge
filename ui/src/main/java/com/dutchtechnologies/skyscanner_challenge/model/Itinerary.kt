package com.dutchtechnologies.skyscanner_challenge.model

import android.os.Parcel
import android.os.Parcelable

data class Itinerary (val price:String, val agent:String, val rating:String, val legs:List<Leg>):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Leg.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(price)
        parcel.writeString(agent)
        parcel.writeString(rating)
        parcel.writeTypedList(legs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Itinerary> {
        override fun createFromParcel(parcel: Parcel): Itinerary {
            return Itinerary(parcel)
        }

        override fun newArray(size: Int): Array<Itinerary?> {
            return arrayOfNulls(size)
        }
    }
}
