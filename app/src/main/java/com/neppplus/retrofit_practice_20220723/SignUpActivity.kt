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

    var emailOk = false
    var nickOk = false

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

//        회원 가입 로직
        mBinding.signUpBtn.setOnClickListener {
//            1. 이메일 중복 체크를 했는가? (멤버변수로 Boolean 값 - emailOk = true / false)
            if (!emailOk) {
                Toast.makeText(mContext, "이메일 중복체크를 해주세요.", Toast.LENGTH_SHORT).show()
//                setOnClickListener 아래 코드를 스킵하고 탈출하는 코드(반복문 - Break)
                return@setOnClickListener
            }

//            2. 닉네임 중복 체크를 했는가?
            if (!nickOk) {
                Toast.makeText(mContext, "닉네임 중복체크를 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            3. 비밀번호를 입력하였는가?
            val inputPw = mBinding.passwordEdt.text.toString()
//            isEmpty : String 값이 비어있는가? (공백 인정)
//            isBlank : String 값이 비어있는가? (공백 인정 X)
            if (inputPw.isBlank()) {
//                비밀번호가 공백 값이거나 or 빈 값
                Toast.makeText(mContext, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            4. 회원가입 로직 실행

        }
    }

    override fun setValues() {

    }

//    [도전과제]
    fun checkValue() {
//        이메일과 닉네임을 동시에 중복 검사할 수 있는 로직으로 작성(생성자를 통해서 분류를 생각해보자)
    }
}