package com.neppplus.retrofit_practice_20220723

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofit_practice_20220723.databinding.ActivityProfileBinding
import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import com.neppplus.retrofit_practice_20220723.utils.ContextUtil
import com.neppplus.retrofit_practice_20220723.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : BaseActivity() {

    lateinit var mBinding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        닉네임 변경 버튼 클릭 이벤트 처리
        mBinding.changeNickBtn.setOnClickListener {
            if (mBinding.changeNickEdt.visibility == View.GONE) {
                mBinding.changeNickEdt.visibility = View.VISIBLE
                mBinding.changeNickBtn.text = "변경 취소"
            }
            else {
                mBinding.changeNickEdt.visibility = View.GONE
                mBinding.changeNickBtn.text = "닉네임 변경"
            }
        }

//        비밀번호 변경 에딧텍스트 이벤트 처리
        mBinding.changePwEdt.addTextChangedListener {
            if (!it.toString().isBlank()) {
                mBinding.currentPwEdt.visibility = View.VISIBLE
            } else {
                mBinding.currentPwEdt.visibility = View.GONE
            }
        }

//        위에 작성된 변경내용 서버에 전달하는 로직 작성
        mBinding.saveChangedBtn.setOnClickListener {

//        분기처리 필요 > 실제로 닉네임을 변경하는가? / 비밀번호를 변경하는가?
//        변수로 우리가 필요한 EditText를 만들어주자.
            var inputNick : String? = mBinding.changeNickEdt.text.toString()
            var currentPw : String? = mBinding.currentPwEdt.text.toString()
            var newPw : String? = mBinding.changePwEdt.text.toString()

            if (inputNick!!.isBlank()) {
//            닉네임 변경 X > 닉네임에 대한 생성자를 null값을 넣어준다.
                inputNick = null
            }

            if (newPw!!.isBlank()) {
//            비밀번호 변경 X > currentPw, newPw 둘 다 null
                currentPw = null
                newPw = null
            }
            val token = ContextUtil.getLoginToken(mContext)

            apiList.getRequestEditUserInfo(
                token, currentPw, newPw, inputNick
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    if (response.isSuccessful) {
                        val br = response.body()!!
                        ContextUtil.setLoginToken(mContext, br.data.token)
                        GlobalData.loginUser = br.data.user

                        Toast.makeText(mContext, "회원 정보 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val message = jsonObj.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }

//        회원 탈퇴 로직 작성
    }

    override fun setValues() {
        mBinding.currentNickTxt.text = GlobalData.loginUser?.nick_name
    }
}