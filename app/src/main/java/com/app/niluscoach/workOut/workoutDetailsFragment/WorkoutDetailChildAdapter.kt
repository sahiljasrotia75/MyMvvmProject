package com.app.niluscoach.workOut.workoutDetailsFragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.homeFragment.HomeModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.workout_child_adapter.view.*


class WorkoutDetailChildAdapter(var context: Context


) : RecyclerView.Adapter<WorkoutDetailChildAdapter.ViewHolder>() {


    var child = ArrayList<HomeModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.workout_child_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return child.size

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = child[position]
Glide.with(context)
    .load(list.image)
    .into(holder.itemView.imgExercise)

    }

    fun updateData(childArray: ArrayList<HomeModel>) {
        child = childArray
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }


}
