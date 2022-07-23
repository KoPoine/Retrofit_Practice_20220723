package com.neppplus.retrofit_practice_20220723

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofit_practice_20220723.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    lateinit var mBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
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