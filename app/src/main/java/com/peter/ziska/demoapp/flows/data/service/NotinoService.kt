package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.flows.data.service.model.ProductsByIdDto
import retrofit2.Call
import retrofit2.http.GET

interface NotinoService {

    @GET("9e471a9b-df5e-4bb2-9991-3127b3d8108a")
    fun getProducts(): Call<ProductsByIdDto>
}
