package com.app.niluscoach.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.workOut.WorkOutActivity
import com.bumptech.glide.Glide


class SuggestedWorkoutAdapter(var context: Context) : RecyclerView.Adapter<SuggestedWorkoutAdapter.ViewHolder>() {

    var mData = ArrayList<HomeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.suggested_layout_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]


        val mParams =
            LinearLayout.LayoutParams(
                getScreenWidth() / 2.9.toInt(),
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        holder.itemView.layoutParams = mParams


        //set the click
        holder.itemView.setOnClickListener {
            val intent= Intent(context, WorkOutActivity::class.java)
            context.startActivity(intent)
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun update(it: ArrayList<HomeModel>) {
        mData = it
        notifyDataSetChanged()

    }

}
