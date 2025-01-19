package com.mskinik.products.data.network

sealed class Resource<T>() {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: Throwable? = null) : Resource<T>()
    data class Fail<T>(val data: T? = null) : Resource<T>()
}