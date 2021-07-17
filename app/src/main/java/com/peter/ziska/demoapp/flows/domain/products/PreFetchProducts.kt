package com.peter.ziska.demoapp.flows.domain.products

import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.base.usecase.BaseWithoutParameterUseCaseSuspandable
import com.peter.ziska.demoapp.flows.data.service.RestError
import javax.inject.Inject


class PreFetchProducts @Inject constructor(
    private val productRepository: ProductRepository,
) : BaseWithoutParameterUseCaseSuspandable<Either<RestError, Unit>>() {

    override suspend fun run(): Either<RestError, Unit> = productRepository.preFetchProducts()
}