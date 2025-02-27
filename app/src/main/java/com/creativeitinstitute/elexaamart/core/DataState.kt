package com.creativeitinstitute.elexaamart.core

sealed class DataState <T>(
    var message:String? = null,
    var data:T? = null
) {
    class Loading<T> : DataState<T>()
    class Success <T> (jData: T?) : DataState<T>(data = jData)
    class Error<T> (message: String): DataState<T>(message)

}