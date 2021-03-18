package com.dabenxiang.mvvm.model.api

import com.dabenxiang.mvvm.model.api.vo.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int = 0,
        @Query("per_page") per_page: Int = 20
    ): Response<ArrayList<User>>

}