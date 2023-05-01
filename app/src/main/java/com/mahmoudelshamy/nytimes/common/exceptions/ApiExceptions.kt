package com.mahmoudelshamy.nytimes.common.exceptions

data class ErrorResponseException(val statusCode: Int, override val message: String = "") : Throwable()

object NoResponseException : Throwable()