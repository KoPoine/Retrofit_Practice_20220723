package com.neppplus.retrofit_practice_20220723

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.neppplus.retrofit_practice_20220723.api.APIList
import com.neppplus.retrofit_practice_20220723.api.ServerApi
import retrofit2.Retrofit

abstract class BaseActivity : AppCompatActivity() {

    //    1. 물려줄 멤버변수
//    val mContext = this // 임시방편
    lateinit var mContext : Context

    //    retrofit 멤버 변수
    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

//    커스텀 액션바 버튼 관련 멤버 변수
    lateinit var backBtn : ImageView
    lateinit var titleTxt : TextView
    lateinit var userImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        retrofit 관련 클래스 및 인터페이스 초기화
        retrofit = ServerApi.getRetrofit()
        apiList = retrofit.create(APIList::class.java)

        mContext = this

//        액션바가 존재하는지 확인 후(?) > 있다면 커스텀 액션바 함수 진행
//        if (supportActionBar != null) {
//            setCustomActionBar()
//        }

//        액션바가 존재하는지 확인 후(?) > 있다면 let으로 함수 진행
        supportActionBar?.let {
            setCustomActionBar()
        }
    }


//    2. 물려줄 함수(추상함수)
    abstract fun setupEvents()

    abstract fun setValues()

    fun setCustomActionBar() {
//        기존의 액션바를 담아줄 변수 생성
        val defaultActionBar = supportActionBar!!

//        커스텀 모드로 변경 > 우리가 만든 xml로 적용
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.my_custom_actionbar)

//        양 옆에 여백 제거 -> 모든 영역이 커스텀 뷰
        val myToolbar = defaultActionBar.customView.parent as Toolbar
        myToolbar.setContentInsetsAbsolute(0,0)

        backBtn = defaultActionBar.customView.findViewById(R.id.backBtn)
        titleTxt = defaultActionBar.customView.findViewById(R.id.titleTxt)
        userImg = defaultActionBar.customView.findViewById(R.id.userImg)
    }
}