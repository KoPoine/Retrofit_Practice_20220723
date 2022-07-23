package com.neppplus.retrofit_practice_20220723

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofit_practice_20220723.api.APIList
import com.neppplus.retrofit_practice_20220723.api.ServerApi
import com.neppplus.retrofit_practice_20220723.databinding.ActivityLoginBinding
import retrofit2.Retrofit
import retrofit2.create

class LoginActivity : BaseActivity() {

    lateinit var mBinding : ActivityLoginBinding

//    retrofit 멤버 변수
    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

//        retrofit 관련 클래스 및 인터페이스 초기화
        retrofit = ServerApi.getRetrofit()
        apiList = retrofit.create(APIList::class.java)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        mBinding.loginBtn.setOnClickListener {
            val inputEmail = mBinding.emailEdt.text.toString()
            val inputPw = mBinding.passwordEdt.text.toString()

//            로그인 API 연결 로직
            val myIntent = Intent(mContext, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    override fun setValues() {

    }
}