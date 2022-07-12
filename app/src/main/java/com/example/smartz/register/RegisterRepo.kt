package com.example.smartz.register

import androidx.lifecycle.LiveData
import com.example.smartz.model.*
import com.example.smartz.network.*


public class RegisterRepo{

    /*
    this is to register user
     */
    fun registerUser(user:RegisterModel): LiveData<NetworkResponseType<NetworkResponse>> {

        return object : NetworkRequestsRepository<NetworkResponse>(){
            override fun createCall(): LiveData<ApiResponse<NetworkResponse>> {
                return  NetworkModule.provideRetrofitInterface().registerUser(user)
            }
        }.toLiveData()
    }
}