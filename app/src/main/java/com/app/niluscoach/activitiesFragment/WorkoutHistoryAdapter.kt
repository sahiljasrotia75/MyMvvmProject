package com.app.niluscoach.activitiesFragment

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
import com.app.niluscoach.homeFragment.HomeModel
import com.app.niluscoach.workOut.WorkOutActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.work_out_history_adapter.view.*


class WorkoutHistoryAdapter(
    var context: Context

) : RecyclerView.Adapter<WorkoutHistoryAdapter.ViewHolder>() {

    var mData = ArrayList<HomeModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.work_out_history_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]

        val mParams =
            LinearLayout.LayoutParams(
                getScreenWidth() / 2.2.toInt(),
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        holder.itemView.layoutParams = mParams
        holder.itemView.txtExercise.text=model.workoutName
        holder.itemView.txtWorkOutName.text=model.time


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
