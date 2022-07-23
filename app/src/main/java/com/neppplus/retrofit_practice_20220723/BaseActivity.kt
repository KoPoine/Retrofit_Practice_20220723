package com.neppplus.retrofit_practice_20220723

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    //    1. 물려줄 멤버변수
    val mContext = this // 임시방편


//    2. 물려줄 함수(추상함수)
    abstract fun setupEvents()

    abstract fun setValues()

}