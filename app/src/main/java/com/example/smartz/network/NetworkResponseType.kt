package com.example.smartz.network



/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class NetworkResponseType<out T>(val status: ResponseStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): NetworkResponseType<T> {
            return NetworkResponseType(ResponseStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): NetworkResponseType<T> {
            return NetworkResponseType(ResponseStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): NetworkResponseType<T> {
            return NetworkResponseType(ResponseStatus.LOADING, data, null)
        }
        fun <T> emptyResponse(data: T?): NetworkResponseType<T> {
            return NetworkResponseType(ResponseStatus.EMPTY, data, null)
        }
    }
}
