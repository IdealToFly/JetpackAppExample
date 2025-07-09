package com.example.app.model.service


import com.example.app.model.NetWork
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticleService {

    @GET("article/list")
    suspend fun list(@Query("pageOffset") pageOffset: Int, @Query("pageSize") pageSize: Int)

    companion object {
        fun instance(): ArticleService {
            return NetWork.createService(ArticleService::class.java)
        }
    }

}