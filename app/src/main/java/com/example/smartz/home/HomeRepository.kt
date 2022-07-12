package com.example.smartz.home

import androidx.lifecycle.LiveData
import com.example.smartz.Sensor
import com.example.smartz.model.RegisterModel
import com.example.smartz.model.SlotDetail
import com.example.smartz.network.*

public class HomeRepository {

    fun getSlots(): LiveData<NetworkResponseType<SlotDetail>> {

        return object : NetworkRequestsRepository<SlotDetail>(){
            override fun createCall(): LiveData<ApiResponse<SlotDetail>> {
                return  NetworkModule.provideRetrofitInterface().getSlots()
            }
        }.toLiveData()
    }
    fun updateSlots(list:List<Sensor>): LiveData<NetworkResponseType<NetworkResponse>> {

        return object : NetworkRequestsRepository<NetworkResponse>(){
            override fun createCall(): LiveData<ApiResponse<NetworkResponse>> {
                return  NetworkModule.provideRetrofitInterface().UpdateSlot(list)
            }
        }.toLiveData()
    }
}