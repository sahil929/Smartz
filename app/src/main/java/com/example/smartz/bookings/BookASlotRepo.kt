package com.example.smartz.bookings

import androidx.lifecycle.LiveData
import com.example.smartz.model.BookSlotModel
import com.example.smartz.model.RegisterModel
import com.example.smartz.network.*

class BookASlotRepo {



    /*
   this is to register user
    */
    fun bookASlot(user: BookSlotModel): LiveData<NetworkResponseType<NetworkResponse>> {

        return object : NetworkRequestsRepository<NetworkResponse>(){
            override fun createCall(): LiveData<ApiResponse<NetworkResponse>> {
                return  NetworkModule.provideRetrofitInterface().bookASlot(user)
            }
        }.toLiveData()
    }
}