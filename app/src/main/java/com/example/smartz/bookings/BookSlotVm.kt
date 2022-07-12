package com.example.smartz.bookings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartz.base.BaseVM
import com.example.smartz.base.Event
import com.example.smartz.model.BookSlotModel
import com.example.smartz.model.RegisterModel
import com.example.smartz.network.NetworkResponse
import com.example.smartz.network.NetworkResponseType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class BookSlotVm: BaseVM()  {

    var clickToSelectDate = MutableLiveData<Event<Unit>>()
    var mBookASlotRepo: BookASlotRepo

    init {
        mBookASlotRepo = BookASlotRepo()
    }

    /*
   this is to BookSlot user
    */
    fun bookaSlot(user: BookSlotModel): LiveData<NetworkResponseType<NetworkResponse>> {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<BookSlotModel> =
            moshi.adapter<BookSlotModel>(BookSlotModel::class.java)

        val json = jsonAdapter.toJson(user)
        Log.e("RegisteredPostJson", "" + json)

        return mBookASlotRepo.bookASlot(user)
    }


    public fun clickToSelectDate(){
        clickToSelectDate.postValue(Event(Unit))
    }
}