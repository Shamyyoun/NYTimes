package com.mahmoudelshamy.nytimes.core

import com.mahmoudelshamy.nytimes.common.exceptions.ErrorResponseException
import com.mahmoudelshamy.nytimes.common.exceptions.NoResponseException
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception

abstract class BaseRepository {

    @Throws(NoResponseException::class, ErrorResponseException::class)
    protected fun <R> handleApiResponse(response: Response<R>): R {
        if (response.isSuccessful) {
            return handleSuccessfulResponse(response)
        } else {
            throw handleErrorResponse(response)
        }
    }

    private fun <R> handleSuccessfulResponse(response: Response<R>): R {
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw NoResponseException
        }
    }

    private fun <R> handleErrorResponse(response: Response<R>): Throwable {
        try {
            val errorResponse = response.errorBody()?.string().orEmpty()
            val jsonObject = JSONObject(errorResponse)
            val message = when {
                jsonObject.has("message") -> jsonObject.getString("message")
                jsonObject.has("Message") -> jsonObject.getString("Message")
                else -> ""
            }

            throw ErrorResponseException(response.code(), message)
        } catch (e: Exception) {
            throw ErrorResponseException(response.code())
        }
    }
}