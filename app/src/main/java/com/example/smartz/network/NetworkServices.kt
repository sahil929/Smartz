package com.example.smartz.network

import androidx.lifecycle.LiveData
import com.example.smartz.Sensor
import com.example.smartz.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import retrofit2.http.Body


interface NetworkServices {
    /*@POST("atm-agency-0.0.1-SNAPSHOT/api/v1/users/register")
    fun loginUser(@Body data: RegisterModel): LiveData<ApiResponse<LoginModel>>
    */
    @POST("registartionform")
    fun registerUser(@Body data:RegisterModel): LiveData<ApiResponse<NetworkResponse>>

    @GET("slotdetailsvalue")
    fun getSlots(): LiveData<ApiResponse<SlotDetail>>


    @GET("bookingdetails")
    fun getBooking(): LiveData<ApiResponse<BookingModel>>

    @POST("uservalidation")
    fun loginUser(@Body data:LoginModel): LiveData<ApiResponse<LoginResponseModel>>

    @POST("addbooking")
    fun bookASlot(@Body data:BookSlotModel): LiveData<ApiResponse<NetworkResponse>>

    @POST("updateslotstatus")
    fun UpdateSlot(@Body data:List<Sensor>): LiveData<ApiResponse<NetworkResponse>>
}