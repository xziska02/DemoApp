package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.flows.data.service.model.ProductsByIdDto

interface NotinoApi {

    suspend fun getProducts(timeoutInMillis: Long = 30000L): Either<RestResult, ProductsByIdDto>
}
