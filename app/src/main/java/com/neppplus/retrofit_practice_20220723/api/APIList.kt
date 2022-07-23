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

//    회원가입
    @FormUrlEncoded
    @PUT("/user")
    fun getRequestSignUp (
    @Field("email") email : String,
    @Field("password") password : String,
    @Field("nick_name") nickname : String
    ) : Call<BasicResponse>

//    이메일 중복 체크
    @GET("/user_check")
    fun getRequestCheckValue (
    @Query("type") type : String,
    @Query("value") value : String,
) : Call<BasicResponse>

//    내 정보 조회
    @GET("/user_info")
    fun getRequestMyInfo (
    @Header("X-Http-Token") token : String
    ) : Call<BasicResponse>
}