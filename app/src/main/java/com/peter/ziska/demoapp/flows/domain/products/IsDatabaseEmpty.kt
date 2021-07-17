package com.peter.ziska.demoapp.flows.domain.products

import com.peter.ziska.demoapp.base.usecase.BaseWithoutParameterUseCase
import javax.inject.Inject


class IsDatabaseEmpty @Inject constructor(
    private val productRepository: ProductRepository,
) : BaseWithoutParameterUseCase<Boolean>() {

    override fun run() = productRepository.isDatabaseEmtpy()
}