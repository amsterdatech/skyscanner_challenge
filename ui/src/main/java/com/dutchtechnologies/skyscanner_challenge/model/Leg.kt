package com.dutchtechnologies.skyscanner_challenge.model

import android.os.Parcel
import android.os.Parcelable

data class Leg(
    val carrierLogo: String,
    val carrierName: String,
    val origin: String,
    val originCode: String,
    val destination: String,
    val destinationCode: String,
    val departure: String,
    val arrival: String,
    val duration: String,
    val direction: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(carrierLogo)
        parcel.writeString(carrierName)
        parcel.writeString(origin)
        parcel.writeString(originCode)
        parcel.writeString(destination)
        parcel.writeString(destinationCode)
        parcel.writeString(departure)
        parcel.writeString(arrival)
        parcel.writeString(duration)
        parcel.writeInt(direction)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Leg> {
        override fun createFromParcel(parcel: Parcel): Leg {
            return Leg(parcel)
        }

        override fun newArray(size: Int): Array<Leg?> {
            return arrayOfNulls(size)
        }
    }

}
