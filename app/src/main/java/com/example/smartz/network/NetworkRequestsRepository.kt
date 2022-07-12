package com.example.smartz.network

import android.net.NetworkRequest
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class NetworkRequestsRepository<Response> constructor(/*private val executor:NetworkExecutors*/) {

    private val result = MediatorLiveData<NetworkResponseType<Response>>()
    lateinit var value: LiveData<ApiResponse<Response>>

    init {
        result.value = NetworkResponseType.loading(null)
        fetchDataFromServer()
    }


    private fun fetchDataFromServer() {

        var callResult = createCall()
        result.addSource(callResult) {

            result.removeSource(callResult)

            when (it) {


                is ApiSuccessResponse -> {


                    result.value = NetworkResponseType.success(it.body)

                }

                is ApiErrorResponse -> {

                    result.value = NetworkResponseType.error(it.errorMessage, null)


                }
                is ApiEmptyResponse -> {

                    result.value = NetworkResponseType.emptyResponse(null)


                }
            }


        }

    }

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<Response>>

    fun toLiveData() = result as LiveData<NetworkResponseType<Response>>


}

