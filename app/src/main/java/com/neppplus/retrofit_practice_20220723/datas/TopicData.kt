package com.neppplus.retrofit_practice_20220723.datas

import java.io.Serializable

data class TopicData(
    val id : Int,
    val title : String,
    val img_url : String,
    val reply_count : Int,
    val sides : ArrayList<SideData>
) : Serializable
