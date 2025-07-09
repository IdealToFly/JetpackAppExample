package com.example.app.model

import com.example.app.model.service.HomeService
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import kotlin.reflect.KClass

object NetWork {

    // mock数据请求
    private const val baseUrl = "https://mock.apipost.cn/app/mock/project/ced69cf2-9206-4a42-895e-dd7442a888df/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).build()

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

}
