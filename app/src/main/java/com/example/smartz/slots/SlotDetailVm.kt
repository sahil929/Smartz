package com.example.smartz.slots

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartz.base.BaseVM
import com.example.smartz.base.Event
import com.example.smartz.home.HomeRepository
import com.example.smartz.model.Booking
import com.example.smartz.model.BookingModel
import com.example.smartz.model.Item
import com.example.smartz.model.SlotDetail
import com.example.smartz.network.NetworkResponseType


class SlotDetailVm : BaseVM(){

    var slotName :MutableLiveData<String> = MutableLiveData()
    var slotLoction:MutableLiveData<String> = MutableLiveData()
    var bookSlotButtonClicked = MutableLiveData<Event<Unit>>()
    var slotId:Int =0
    var slotDetailRepo: SlotDetailRepo
    init {
        slotDetailRepo = SlotDetailRepo()
    }

   public var slotDetail: SlotDetail?=null
    fun updateItemData(item: SlotDetail){
        slotDetail = item
    }
    public fun getBooking(): LiveData<NetworkResponseType<BookingModel>> {
      return  slotDetailRepo.getBooking()
    }
    public fun startBookSlot(){
      bookSlotButtonClicked.postValue(Event(Unit))
    }
}