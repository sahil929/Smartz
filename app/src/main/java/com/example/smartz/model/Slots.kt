package com.example.smartz.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class SlotsDetail(
   // val Count: Int,
    val Items: List<Item>
   // val ScannedCount: Int
)

data class Item(
    val Slot: ArrayList<String>?,
    val slotId: Int,
    val slotName: String? ="Altran",
    val slotLocation: String?,
    val slotStatus: Int,
    val startDate: String?,
    val userMobile: Long
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(Slot)
        parcel.writeInt(slotId)
        parcel.writeString(slotName)
        parcel.writeString(slotLocation)
        parcel.writeInt(slotStatus)
        parcel.writeString(startDate)
        parcel.writeLong(userMobile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}



