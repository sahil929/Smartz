package com.example.smartz.model

data class SlotDetail(
    val body: List<Body>,
    val slotId: Int,
    val slotLocation: String,
    val slotName: String
)

data class Body(
    val parkingId: Int,
    val parkingLocation: String,
    val parkingName: String,
    val parkingStatus: String
)
