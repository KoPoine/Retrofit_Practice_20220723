package com.neppplus.retrofit_practice_20220723

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.retrofit_practice_20220723.adapters.TopicRecyclerViewAdapter
import com.neppplus.retrofit_practice_20220723.databinding.ActivityMainBinding
import com.neppplus.retrofit_practice_20220723.datas.TopicData

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

    }

    //    첫 화면 동작시 데이터 연결 (초기화)
    override fun setValues() {

        mTopicAdapter = TopicRecyclerViewAdapter(mContext, mTopicList)
        mBinding.mainRecyclerView.adapter = mTopicAdapter
        mBinding.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}