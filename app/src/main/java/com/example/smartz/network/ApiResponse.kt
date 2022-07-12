package com.example.smartz.network

import android.util.Log
import com.example.smartz.model.MyErrorModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.Response


sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            Log.e("msg",""+error)

            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            Log.e("response",""+response.body())

            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body
                    )
                }
            } else {

                var msg = response.errorBody()?.string()
                val moshi = Moshi.Builder().build()

                val type = Types.newParameterizedType(
                    MyErrorModel::class.java
                    )
                val adapter: JsonAdapter<MyErrorModel> = moshi.adapter(type)

                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                  var err:MyErrorModel? = adapter.fromJson(msg)
                    if(err?.message.isNullOrEmpty()){
                        err?.error
                    }
                    else{
                      err?.message
                    }
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}
//9957577686
/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T) : ApiResponse<T>() {
    constructor(body: T, linkHeader: String?) : this(
        body = body
    )

}

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()