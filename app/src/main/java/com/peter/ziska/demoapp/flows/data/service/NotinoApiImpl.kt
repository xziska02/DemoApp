package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.flows.data.service.model.ProductsByIdDto
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import javax.inject.Inject

class NotinoApiImpl @Inject constructor(
    private val retrofit: Retrofit,
) : NotinoApi, ApiHelper() {

    private val service by lazy { retrofit.create(NotinoService::class.java) }

    override suspend fun getProducts(timeoutInMillis: Long): Either<RestResult, ProductsByIdDto> =
        tryRequest {
            withTimeout(timeoutInMillis) {
                suspendCancellableCoroutine { continuation ->
                    service.getProducts().enqueue(RetrofitCallbacks(continuation) {
                        Either.Right(it)
                    })
                }
            }
        }
}
