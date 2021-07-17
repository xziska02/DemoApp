package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import kotlinx.coroutines.CancellableContinuation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

class RetrofitCallbacks<T, S>(
    private val continuation: CancellableContinuation<Either<RestError, T>>,
    private val onResponse: (S) -> Either<RestError, T>
) : Callback<S> {
    override fun onFailure(call: Call<S>, t: Throwable) {
        t.printStackTrace()
        continuation.resume(Either.Left(RestError.Error("Error: $t")))
    }

    override fun onResponse(
        call: Call<S>,
        response: Response<S>
    ) {
        response.body()?.let {
            continuation.resume(onResponse(it))
        } ?: continuation.resume(Either.Left(RestError.Error("Null body")))
    }
}