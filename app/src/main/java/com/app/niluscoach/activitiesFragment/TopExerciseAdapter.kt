package com.app.niluscoach.activitiesFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.homeFragment.HomeModel
import kotlinx.android.synthetic.main.top_exercise_adapter.view.*


class TopExerciseAdapter(
    var context: Context

) : RecyclerView.Adapter<TopExerciseAdapter.ViewHolder>() {

    var mData = ArrayList<HomeModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.top_exercise_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]

   holder.itemView.txtExerciseName.text=model.workoutName
   holder.itemView.txtMinutes.text=model.time
  // holder.itemView.txtTime.text=model.workoutName



    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    fun update(it: ArrayList<HomeModel>) {
        mData = it
        notifyDataSetChanged()

    }

}
