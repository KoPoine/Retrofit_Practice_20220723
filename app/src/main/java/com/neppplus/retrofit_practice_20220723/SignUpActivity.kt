package com.neppplus.retrofit_practice_20220723

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofit_practice_20220723.databinding.ActivitySignUpBinding
import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var mBinding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        이메일 중복 확인버튼 클릭 이벤트 처리
        mBinding.checkEmailBtn.setOnClickListener {
            val inputEmail = mBinding.emailEdt.text.toString()

            apiList.getRequestCheckValue("EMAIL", inputEmail)
                .enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            Log.d("응답", br.toString())
                            mBinding.checkEmailTxt.text = br.message
                            Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val errorBodyStr = response.errorBody()!!.string()
                            val jsonObj = JSONObject(errorBodyStr)
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            mBinding.checkEmailTxt.text = "중복 검사를 해주세요."
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
        }

//        닉네임 중복 확인버튼 클릭 이벤트 처리
        mBinding.checkNickBtn.setOnClickListener {
            val inputNick = mBinding.nickEdt.text.toString()
            apiList.getRequestCheckValue("NICK_NAME", inputNick)
                .enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
//                            성공 된 값
                            val br = response.body()!!
                            mBinding.checkNickTxt.text = br.message
                            Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()
                        }
                        else {
//                            errorBody를 Json Parsing
                            val errorBodyStr = response.errorBody()!!.string()
                            val jsonObj = JSONObject(errorBodyStr)
                            val message = jsonObj.getString("message")

                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            mBinding.checkNickTxt.text = "중복 검사를 해주세요."
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
        }
    }

    override fun setValues() {

    }

//    [도전과제]
    fun checkValue() {
//        이메일과 닉네임을 동시에 중복 검사할 수 있는 로직으로 작성(생성자를 통해서 분류를 생각해보자)
    }
}