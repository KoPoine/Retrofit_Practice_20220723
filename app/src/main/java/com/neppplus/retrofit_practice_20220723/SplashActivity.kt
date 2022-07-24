package com.neppplus.retrofit_practice_20220723

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import com.neppplus.retrofit_practice_20220723.utils.ContextUtil
import com.neppplus.retrofit_practice_20220723.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    var isAutoLoginOk = false
    var isTokenOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        가지고 있는 토큰이 유효한가? (토큰 유효성 검사 API 작성)
        val token = ContextUtil.getLoginToken(mContext)
        apiList.getRequestMyInfo(token).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    GlobalData.loginUser = br.data.user
                    isTokenOk = true
                }
                else {
                    isTokenOk = false
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed(
            {
//            1. 실제로 autoLogin을 체크 했는가?
                isAutoLoginOk = ContextUtil.getAutoLogin(mContext)
//            2. 가지고 있는 토큰이 유효한가?
            if (isAutoLoginOk && isTokenOk) {
//                autoLogin 체크 & 토큰 유효성 두 가지 모두 true > 자동 로그인 성공
                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
                finish()
            } else {
//                두가지중 하나라도 false > 자동 로그인 실패
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            }

        }, 2500)
    }
}