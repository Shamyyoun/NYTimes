package com.mahmoudelshamy.nytimes.features.common.domain

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: AppError, val data: T? = null) : Result<T>()
    data class Loading<T>(val data: T? = null) : Result<T>()
}