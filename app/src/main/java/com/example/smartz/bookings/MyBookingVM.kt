package com.example.smartz.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartz.base.BaseVM
import com.example.smartz.home.HomeRepository
import com.example.smartz.model.Body
import com.example.smartz.model.BookingModel
import com.example.smartz.model.SlotDetail
import com.example.smartz.network.NetworkResponseType
import com.example.smartz.slots.SlotDetailRepo

class MyBookingVM: BaseVM() {

    var slotDetailRepo: SlotDetailRepo
    var moveToSlotsDetail: MutableLiveData<Body> = MutableLiveData<Body>()
    init {
        slotDetailRepo = SlotDetailRepo()
    }

    fun getMyBookings(): LiveData<NetworkResponseType<BookingModel>> {

        return slotDetailRepo.getBooking()
    }

}