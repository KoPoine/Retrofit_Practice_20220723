package com.neppplus.retrofit_practice_20220723

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofit_practice_20220723.databinding.ActivityDetailTopicBinding
import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import com.neppplus.retrofit_practice_20220723.datas.TopicData
import com.neppplus.retrofit_practice_20220723.utils.ContextUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTopicActivity : BaseActivity() {

    lateinit var mBinding : ActivityDetailTopicBinding
    lateinit var mTopicData : TopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_topic)
        mTopicData = intent.getSerializableExtra("topicData") as TopicData
        Log.d("토픽 데이터", mTopicData.toString())
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
//가져온 데이터 UI 처리 등등
        val token = ContextUtil.getLoginToken(mContext)
        apiList.getRequestDetailTopic(token, mTopicData.id).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    Log.d("상세 토픽 조회 결과", br.data.topic.toString())
                }
                else {
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val message = jsonObj.getString("message")

                    Log.d("상세 토픽 조회 오류", message)
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })

    }
}