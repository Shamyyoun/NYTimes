package com.mahmoudelshamy.nytimes.features.common.domain.base

import com.mahmoudelshamy.nytimes.features.common.domain.AppError
import com.mahmoudelshamy.nytimes.features.common.domain.Result
import com.mahmoudelshamy.nytimes.features.common.domain.exceptions.ErrorResponseException
import kotlinx.coroutines.flow.FlowCollector
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseUseCase {

    protected suspend inline fun <F> FlowCollector<Result<F>>.tryFlow(func: (FlowCollector<Result<F>>) -> Unit) {
        try {
            func.invoke(this)
        } catch (e: CancellationException) {
            throw e
        } catch (e: IOException) {
            emit(
                Result.Error(
                    error = AppError.NetworkError
                )
            )
        } catch (e: ErrorResponseException) {
            emit(
                Result.Error(
                    error = AppError.ApiErrorMessage(
                        statusCode = e.statusCode,
                        message = e.message
                    )
                )
            )
        } catch (e: Throwable) {
            emit(
                Result.Error(
                    error = AppError.GeneralError
                )
            )
        }
    }
}