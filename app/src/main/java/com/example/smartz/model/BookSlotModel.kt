package com.example.smartz.model

import androidx.databinding.Bindable

data class BookSlotModel (
    var slotStatus: Int?=0,
    // var lastName: String?="",
    var startDate: String?="21-11-2020",
    var userMobile: String?="992333333",
    var Slot: String?="5:00PM-7:00PM",
    var slotId:Int= 0,
    var slotLocation: String?="Laxmi Nagar,Delhi",
    var vehicleNumber: String?="MPO789876"
)