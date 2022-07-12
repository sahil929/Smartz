package com.example.smartz.model

import android.os.Parcel
import android.os.Parcelable

data class BookingModel(
    val body: List<Booking>,
    val statusCode: Int
)

data class Booking(
    val Vehicleno: String?,
    val boookingId: String?,
    val mobileNumber: String?,
    val slot: String?,
    val slotId: Int,
    val slotLocation: String?,
    val slotStatus: Int,
    val startDate: String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Vehicleno)
        parcel.writeString(boookingId)
        parcel.writeString(mobileNumber)
        parcel.writeString(slot)
        parcel.writeInt(slotId)
        parcel.writeString(slotLocation)
        parcel.writeInt(slotStatus)
        parcel.writeString(startDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Booking> {
        override fun createFromParcel(parcel: Parcel): Booking {
            return Booking(parcel)
        }

        override fun newArray(size: Int): Array<Booking?> {
            return arrayOfNulls(size)
        }
    }
}
