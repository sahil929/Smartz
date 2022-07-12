package com.example.smartz.slots

import androidx.lifecycle.LiveData
import com.example.smartz.model.BookingModel
import com.example.smartz.model.SlotDetail
import com.example.smartz.network.ApiResponse
import com.example.smartz.network.NetworkModule
import com.example.smartz.network.NetworkRequestsRepository
import com.example.smartz.network.NetworkResponseType

public class SlotDetailRepo {

    fun getBooking(): LiveData<NetworkResponseType<BookingModel>> {

        return object : NetworkRequestsRepository<BookingModel>(){
            override fun createCall(): LiveData<ApiResponse<BookingModel>> {
                return  NetworkModule.provideRetrofitInterface().getBooking()
            }
        }.toLiveData()
    }
}