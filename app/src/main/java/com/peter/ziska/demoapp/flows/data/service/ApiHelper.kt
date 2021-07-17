package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import kotlinx.coroutines.TimeoutCancellationException
import org.json.JSONException


abstract class ApiHelper {

    protected suspend fun <T> tryRequest(fn: suspend () -> Either<RestError, T>): Either<RestError, T> =
        try {
            fn()
        } catch (exception: TimeoutCancellationException) {
            Either.Left(RestError.Timeout)
        } catch (exception: Exception) {
            exception.printStackTrace()
            Either.Left(RestError.Error("Error: $exception"))
        }
}