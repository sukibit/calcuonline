package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit

import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.model.ApiHeaders
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface CalculatorApi {
    companion object {
        fun defaultHeaders(): Map<String, String> {
            val headers = ApiHeaders()
            return mapOf(
                "content-type" to headers.contentType,
                "accept-language" to headers.acceptLanguage,
                "x-api-key" to headers.xApiKey,
                "x-requested-with" to headers.xRequestedWith,
                "origin" to headers.origin,
                "referer" to headers.referer,
                "user-agent" to headers.userAgent
            )
        }
    }
    @POST("{end_point}")
    suspend fun getCalculatorResult(
        @Path(value = "end_point") endPoint: String,
        @Body body: String,
        @HeaderMap headerMap: Map<String, String> = defaultHeaders(),
    ): String
}
