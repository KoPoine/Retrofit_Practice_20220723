package com.neppplus.retrofit_practice_20220723.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofit_practice_20220723.R
import com.neppplus.retrofit_practice_20220723.datas.TopicData

class TopicRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<TopicData>
        )
    : RecyclerView.Adapter<TopicRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : TopicData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.topic_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}