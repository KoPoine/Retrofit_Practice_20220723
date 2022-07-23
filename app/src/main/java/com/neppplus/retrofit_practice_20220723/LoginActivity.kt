package com.neppplus.retrofit_practice_20220723


import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import com.neppplus.retrofit_practice_20220723.api.APIList
import com.neppplus.retrofit_practice_20220723.api.ServerApi
import com.neppplus.retrofit_practice_20220723.databinding.ActivityLoginBinding
import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import retrofit2.*

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
            apiList.getRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
//                    실제로 response가 도달했을때
                    val responseStr = response.toString()
                    Log.d("로그인", responseStr)
                    if (response.isSuccessful) {
//                        응답이 성공 (로그인 로직 성공 - id / pw 일치)
                        val rb = response.body()
                        Log.d("로그인 성공", rb.toString())
                    }
                    else {
//                        응답 실패 (로그인 로직 실패)
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val message = jsonObj.getString("message")

                        Log.d("로그인 실패", message)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                    어떤한 response도 없을때(응답 없음) > 실제적으로 인터넷 연결 불량
                    Log.d("연결", "실패")
                }
            })
//            val myIntent = Intent(mContext, MainActivity::class.java)
//            startActivity(myIntent)
//            finish()
        }
    }

    override fun setValues() {

    }
}