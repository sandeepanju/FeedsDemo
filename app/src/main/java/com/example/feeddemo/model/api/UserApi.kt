package com.example.feeddemo.model.api

import com.example.feeddemo.model.entity.User
import com.example.feeddemo.pojo.GenericResponse
import com.example.feeddemo.pojo.RequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("list")
    suspend fun getAllAsync(@Body requestModel : RequestModel ): Response<GenericResponse<List<User>>>
}