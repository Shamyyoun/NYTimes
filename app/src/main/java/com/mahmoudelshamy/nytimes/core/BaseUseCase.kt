package com.mahmoudelshamy.nytimes.core

import com.mahmoudelshamy.nytimes.common.AppError
import com.mahmoudelshamy.nytimes.common.Resource
import com.mahmoudelshamy.nytimes.common.exceptions.ErrorResponseException
import kotlinx.coroutines.flow.FlowCollector
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseUseCase {

    protected suspend inline fun <F> FlowCollector<Resource<F>>.tryFlow(func: (FlowCollector<Resource<F>>) -> Unit) {
        try {
            func.invoke(this)
        } catch (e: CancellationException) {
            throw e
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    error = AppError.NetworkError
                )
            )
        } catch (e: ErrorResponseException) {
            emit(
                Resource.Error(
                    error = AppError.ApiErrorMessage(
                        statusCode = e.statusCode,
                        message = e.message
                    )
                )
            )
        } catch (e: Throwable) {
            emit(
                Resource.Error(
                    error = AppError.GeneralError
                )
            )
        }
    }
}