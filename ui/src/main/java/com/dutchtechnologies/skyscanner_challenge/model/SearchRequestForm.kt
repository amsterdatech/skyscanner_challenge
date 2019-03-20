package com.dutchtechnologies.skyscanner_challenge.model

import android.os.Parcel
import android.os.Parcelable

data class SearchRequestForm(
    val apiKey: String = "",
    val cabinClass: String = "",
    val country: String = "",
    val currency: String = "",
    val locale: String = "",
    val locationSchema: String = "",
    val originPlace: String = "",
    val destinationPlace: String = "",
    val outbounddate: String = "",
    val inbounddate: String = "",
    val adults: Int = 0,
    val children: Int = 0,
    val infants: Int = 0,
    var pageIndex:Int = 1
):Parcelable {
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
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(apiKey)
        parcel.writeString(cabinClass)
        parcel.writeString(country)
        parcel.writeString(currency)
        parcel.writeString(locale)
        parcel.writeString(locationSchema)
        parcel.writeString(originPlace)
        parcel.writeString(destinationPlace)
        parcel.writeString(outbounddate)
        parcel.writeString(inbounddate)
        parcel.writeInt(adults)
        parcel.writeInt(children)
        parcel.writeInt(infants)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchRequestForm> {
        override fun createFromParcel(parcel: Parcel): SearchRequestForm {
            return SearchRequestForm(parcel)
        }

        override fun newArray(size: Int): Array<SearchRequestForm?> {
            return arrayOfNulls(size)
        }
    }
}