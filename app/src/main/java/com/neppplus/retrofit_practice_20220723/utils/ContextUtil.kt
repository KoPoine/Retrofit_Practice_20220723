package com.neppplus.retrofit_practice_20220723.utils

import android.content.Context

class ContextUtil {

//    앱 전역에서 클래스의 함수(get/set)를 활용하기 위함.

    companion object {
//        1. 변수 생성
//        (0) 메모장 이름 설정
        val prefName = "my_pref"
//        (1) 자동 로그인
        val AUTO_LOGIN = "AUTO_LOGIN"
//        (2) 로그인 토큰 저장
        val LOGIN_TOKEN = "LOGIN_TOKEN"

//        2. set / get 함수 생성

        fun setAutoLogin(context : Context, autoLogin : Boolean) {
//            메모장 열기
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            실제 메모장에 데이터 저장
            pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply()
        }

        fun getAutoLogin(context : Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)
        }

        fun setLoginToken(context: Context, token : String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_TOKEN, token).apply()
        }

        fun getLoginToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_TOKEN, "")!!
        }
    }

}