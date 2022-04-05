package com.example.tatvasofttest.network

import com.example.tatvasofttest.Response.MainTestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkService {

    @GET
    suspend fun mainTest(@Url url: String): Response<MainTestResponse>

}