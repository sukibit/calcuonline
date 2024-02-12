package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.model

data class ApiHeaders(
    val contentType: String = "application/x-www-form-urlencoded; charset=UTF-8",
    val acceptLanguage: String = "es",
    val xApiKey: String = "04565723d9fb4064375282fd18f38c1f6c5eb27505b3e2398ed93b5cd85cafd0",
    val xRequestedWith: String = "XMLHttpRequest",
    val origin: String = "https://calcuonline.com",
    val referer: String = "https://calcuonline.com",
    val userAgent: String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
)