package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import kotlinx.coroutines.TimeoutCancellationException


abstract class ApiHelper {

    protected suspend fun <T> tryRequest(fn: suspend () -> Either<RestResult, T>): Either<RestResult, T> =
        try {
            fn()
        } catch (exception: TimeoutCancellationException) {
            Either.Left(RestResult.Timeout)
        } catch (exception: Exception) {
            exception.printStackTrace()
            Either.Left(RestResult.Error("Error: $exception"))
        }
}