package com.neppplus.retrofit_practice_20220723

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.retrofit_practice_20220723.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}