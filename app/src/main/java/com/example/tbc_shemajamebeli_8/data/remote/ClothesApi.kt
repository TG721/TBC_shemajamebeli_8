package com.example.tbc_shemajamebeli_8.data.remote

import com.example.tbc_shemajamebeli_8.data.model.DPO
import retrofit2.Response
import retrofit2.http.GET

interface ClothesApi {
    @GET("v3/05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun doNetworkCall(): Response<DPO>

}