package com.example.kotlindemo

import retrofit2.http.GET
import retrofit2.http.Path

interface homeAPI {
    @GET("get_User_Detail/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: Int) : User

    @GET("/users/")
    suspend fun getUserList(): List<User>
}