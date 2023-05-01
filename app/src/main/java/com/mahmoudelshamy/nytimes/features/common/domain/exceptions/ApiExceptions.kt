package com.mahmoudelshamy.nytimes.features.common.domain.exceptions

data class ErrorResponseException(val statusCode: Int, override val message: String = "") : Throwable()

object NoResponseException : Throwable()