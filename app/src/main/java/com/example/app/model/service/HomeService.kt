package com.example.app.model.service

import com.example.app.model.NetWork
import com.example.app.model.entity.CategoryResponse
import com.example.app.model.entity.SwiperResponse
import retrofit2.http.GET

interface HomeService {

    @GET("category/list")
    suspend fun category(): CategoryResponse

    @GET("recommand/banner")
    suspend fun banner(): SwiperResponse

    companion object {
        fun instance(): HomeService {
            return NetWork.createService(HomeService::class.java)
        }
    }


}