package com.neppplus.retrofit_practice_20220723

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.retrofit_practice_20220723.databinding.ActivityDetailTopicBinding

class DetailTopicActivity : BaseActivity() {

    lateinit var mBinding : ActivityDetailTopicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_topic)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}