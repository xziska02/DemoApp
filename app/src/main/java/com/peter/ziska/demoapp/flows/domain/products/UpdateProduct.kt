package com.peter.ziska.demoapp.flows.domain.products

import com.peter.ziska.demoapp.base.usecase.BaseParameterUseCase
import com.peter.ziska.demoapp.flows.domain.model.Product
import javax.inject.Inject


class UpdateProduct @Inject constructor(
    private val productRepository: ProductRepository
) : BaseParameterUseCase<Product, Unit>() {

    override fun run(input: Product) {
        productRepository.updateProduct(input)
    }
}