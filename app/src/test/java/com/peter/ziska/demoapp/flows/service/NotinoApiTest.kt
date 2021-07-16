package com.peter.ziska.demoapp.flows.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peter.ziska.demoapp.base.either.Either
import com.peter.ziska.demoapp.base.either.withRight
import com.peter.ziska.demoapp.flows.data.service.NotinoApi
import com.peter.ziska.demoapp.flows.data.service.NotinoApiImpl
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.Test
import retrofit2.Retrofit
import java.net.URL

class NotinoApiTest {

    private val mockWebServer = MockWebServer()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL("http://${mockWebServer.hostName}:${mockWebServer.port}"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Test
    fun `should return success response`() {

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest) =
                MockResponse()
                    .setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody(successResponse)
        }

        val uut: NotinoApi = NotinoApiImpl(retrofit)

        runBlocking {
            val products = uut.getProducts()

            products `should be instance of` Either.Right::class
            products.withRight {
                it.products.size `should be equal to` 2
                it.products[0].let {
                    it.id `should be equal to` 83015
                    it.price.value `should be equal to` 190
                    it.name `should be equal to` "Homme 5 Force Clay"
                    it.attributes.ean `should be equal to` "3474630287273"
                }
            }

        }
    }

    @Test
    fun `should return failure response`() {

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest) =
                MockResponse()
                    .setResponseCode(404)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
        }

        val uut: NotinoApi = NotinoApiImpl(retrofit)

        runBlocking {
            val products = uut.getProducts()

            products `should be instance of` Either.Left::class
        }
    }


    val successResponse = """{
        "vpProductByIds": [
        {
            "id": "83015",
            "productId": 83015,
            "masterId": 83015,
            "price": {
            "value": 190,
            "currency": "CZK"
        },
            "name": "Homme 5 Force Clay",
            "brand": {
            "id": "258",
            "name": "L’Oréal Professionnel"
        },
            "annotation": "modelovací hlína silné zpevnění 50 ml",
            "orderUnit": "ks",
            "attributes": {
            "Master": true,
            "EAN": "3474630287273",
            "PackageSize": {
            "height": 36,
            "width": 65,
            "depth": 65
        }
        },
            "imageUrl": "loreal-professionnel/3474630287273_01-o__26.jpg",
            "stockAvailability": {
            "code": "moreThan20",
            "count": null
        },
            "reviewSummary": {
            "score": 5
        }
        },
        {
            "id": "422769",
            "productId": 422769,
            "masterId": 422769,
            "price": {
            "value": 95,
            "currency": "CZK"
        },
            "name": "Peace",
            "brand": {
            "id": "10782",
            "name": "Axe"
        },
            "annotation": "deodorant ve spreji 150 ml",
            "orderUnit": "ks",
            "attributes": {
            "Master": true,
            "EAN": "8712561194594",
            "PackageSize": {
            "height": 140,
            "width": 52,
            "depth": 52
        }
        },
            "imageUrl": "axe/8712561258036-o__25.jpg",
            "stockAvailability": {
            "code": "moreThan20",
            "count": null
        },
            "reviewSummary": {
            "score": 1
        }
       }
    ]}"""
}