package com.neppplus.retrofit_practice_20220723.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.retrofit_practice_20220723.DetailTopicActivity
import com.neppplus.retrofit_practice_20220723.R
import com.neppplus.retrofit_practice_20220723.datas.TopicData
import org.w3c.dom.Text

class TopicRecyclerViewAdapter (
    val mContext : Context,
    val mList : ArrayList<TopicData>
        )
    : RecyclerView.Adapter<TopicRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : TopicData) {
            val backgroundImg = itemView.findViewById<ImageView>(R.id.backgroundImg)
            val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
            val replyCountTxt = itemView.findViewById<TextView>(R.id.replyCountTxt)

            titleTxt.text = item.title
            replyCountTxt.text = "${item.reply_count}명 참여중"
            Glide.with(mContext)
                .load(item.img_url)
                .into(backgroundImg)

            itemView.setOnClickListener {
                val myIntent = Intent(mContext, DetailTopicActivity::class.java)
                myIntent.putExtra("topicData", item)
                mContext.startActivity(myIntent)
            }
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