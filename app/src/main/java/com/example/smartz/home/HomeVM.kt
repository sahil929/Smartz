package com.example.smartz.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartz.Sensor
import com.example.smartz.base.BaseVM
import com.example.smartz.model.Body
import com.example.smartz.model.RegisterModel
import com.example.smartz.model.SlotDetail
import com.example.smartz.network.NetworkResponse
import com.example.smartz.network.NetworkResponseType
import com.example.smartz.register.RegisterRepo
import com.google.firebase.database.MutableData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class HomeVM: BaseVM() {

    var homeRepository: HomeRepository
    var moveToSlotsDetail:MutableLiveData<Body> = MutableLiveData<Body>()
    init {
        homeRepository = HomeRepository()
    }

    fun getSlots(): LiveData<NetworkResponseType<SlotDetail>> {

        return homeRepository.getSlots()
    }
    fun updateSlots(sensor: List<Sensor>): LiveData<NetworkResponseType<NetworkResponse>> {

        return homeRepository.updateSlots(sensor)
    }

}