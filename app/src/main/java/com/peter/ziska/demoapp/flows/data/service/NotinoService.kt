package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.flows.data.service.model.ProductsByIdDto
import retrofit2.Call
import retrofit2.http.GET

interface NotinoService {


    @GET("michals92/notino-mobile-test/db")
    fun getProducts(): Call<ProductsByIdDto>
}
