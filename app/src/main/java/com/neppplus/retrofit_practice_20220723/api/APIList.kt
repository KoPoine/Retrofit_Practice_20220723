package com.neppplus.retrofit_practice_20220723.api

import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

//    user
//    로그인
    @FormUrlEncoded
    @POST("/user")
    fun getRequestLogin (
    @Field("email") email : String,
    @Field("password") password : String
    ) : Call<BasicResponse>

}