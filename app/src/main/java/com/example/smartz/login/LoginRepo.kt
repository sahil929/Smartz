package com.example.smartz.login

import androidx.lifecycle.LiveData
import com.example.smartz.model.LoginModel
import com.example.smartz.network.*

public class LoginRepo{
   fun loginUser(user: LoginModel): LiveData<NetworkResponseType<LoginResponseModel>> {

        return object : NetworkRequestsRepository<LoginResponseModel>(){
            override fun createCall(): LiveData<ApiResponse<LoginResponseModel>> {

                return  NetworkModule.provideRetrofitInterface().loginUser(user)
            }
        }.toLiveData()
    }
}