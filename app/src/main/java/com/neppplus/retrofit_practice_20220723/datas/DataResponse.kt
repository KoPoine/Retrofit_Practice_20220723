package com.neppplus.retrofit_practice_20220723.datas

data class DataResponse (
    val token : String,
    val user : UserData,
    val topics : ArrayList<TopicData>,
    val topic : TopicData
        ) {
}