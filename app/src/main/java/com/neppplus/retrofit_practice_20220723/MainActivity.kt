package com.neppplus.retrofit_practice_20220723

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.retrofit_practice_20220723.adapters.TopicRecyclerViewAdapter
import com.neppplus.retrofit_practice_20220723.databinding.ActivityMainBinding
import com.neppplus.retrofit_practice_20220723.datas.BasicResponse
import com.neppplus.retrofit_practice_20220723.datas.TopicData
import com.neppplus.retrofit_practice_20220723.utils.ContextUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding

    lateinit var mTopicAdapter : TopicRecyclerViewAdapter
    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    //    이벤트 동작 로직 작성(setOnClickListener)
    override fun setupEvents() {
//        로그아웃 버튼 클릭 이벤트
        mBinding.logoutBtn.setOnClickListener {

            ContextUtil.setLoginToken(mContext, "")

            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    //    첫 화면 동작시 데이터 연결 (초기화)
    override fun setValues() {
        userImg.visibility = View.VISIBLE

        getTopicListFromServer()

        mTopicAdapter = TopicRecyclerViewAdapter(mContext, mTopicList)
        mBinding.mainRecyclerView.adapter = mTopicAdapter
        mBinding.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getTopicListFromServer() {
        val token = ContextUtil.getLoginToken(mContext)
        apiList.getRequestMainInfo(token).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

//                    API 통신 결과 TopicList를 받아와서 mTopicList에 추가
                    mTopicList.addAll(br.data.topics)
                    Log.d("토픽 갯수", mTopicList.size.toString())

//                    우리 어뎁터에 연결된 리스트의 갱신을 확인 코드
                    mTopicAdapter.notifyDataSetChanged()
                }
                else {
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val message = jsonObj.getString("message")

                    Log.d("토픽데이터 송신 에러", message)
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}