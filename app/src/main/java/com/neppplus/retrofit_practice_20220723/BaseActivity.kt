package com.neppplus.retrofit_practice_20220723

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neppplus.retrofit_practice_20220723.api.APIList
import com.neppplus.retrofit_practice_20220723.api.ServerApi
import retrofit2.Retrofit

abstract class BaseActivity : AppCompatActivity() {

    //    1. 물려줄 멤버변수
//    val mContext = this // 임시방편
    lateinit var mContext : Context

    //    retrofit 멤버 변수
    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        retrofit 관련 클래스 및 인터페이스 초기화
        retrofit = ServerApi.getRetrofit()
        apiList = retrofit.create(APIList::class.java)

        mContext = this
    }


//    2. 물려줄 함수(추상함수)
    abstract fun setupEvents()

    abstract fun setValues()

}