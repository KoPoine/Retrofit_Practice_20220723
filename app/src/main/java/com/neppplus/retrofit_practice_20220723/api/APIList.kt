package com.neppplus.retrofit_practice_20220723.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface APIList {

//    user
//    로그인
    @POST("/user")
    fun getRequestLogin (
    @Field("email") email : String,
    @Field("password") password : String
    ) : Call<JsonObject>



}