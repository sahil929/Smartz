package com.example.smartz.network

import com.example.smartz.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule{

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @JvmStatic
     fun provideRetrofitInterface(): NetworkServices {
       val retrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
       return retrofit.create(NetworkServices::class.java)
    }

}