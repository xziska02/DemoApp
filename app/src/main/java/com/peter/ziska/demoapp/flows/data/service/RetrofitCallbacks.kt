package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import kotlinx.coroutines.CancellableContinuation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume

class RetrofitCallbacks<T, S>(
    private val continuation: CancellableContinuation<Either<RestResult, T>>,
    private val onResponse: (S) -> Either<RestResult, T>
) : Callback<S> {
    override fun onFailure(call: Call<S>, t: Throwable) {
        t.printStackTrace()
        continuation.resume(Either.Left(RestResult.Error("Error: $t")))
    }

    override fun onResponse(
        call: Call<S>,
        response: Response<S>
    ) {
        response.body()?.let {
            continuation.resume(onResponse(it))
        } ?: continuation.resume(Either.Left(RestResult.Error("Null body")))
    }
}