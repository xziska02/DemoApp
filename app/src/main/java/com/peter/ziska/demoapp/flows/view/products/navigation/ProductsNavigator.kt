package com.peter.ziska.demoapp.flows.view.products.navigation

import androidx.navigation.NavController
import com.peter.ziska.demoapp.flows.domain.model.Product
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class ProductsNavigator @Inject constructor() {
    lateinit var navController: NavController

    fun init(navController: NavController) {
        this.navController = navController
    }

    fun navigateToProductDetail(product: Product) {
        val json = Json.encodeToString(product)
    }

}