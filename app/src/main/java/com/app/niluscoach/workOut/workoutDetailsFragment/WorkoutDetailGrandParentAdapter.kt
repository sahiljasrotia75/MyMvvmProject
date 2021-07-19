package com.app.niluscoach.workOut.workoutDetailsFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import kotlinx.android.synthetic.main.workout_details_parent_adapter.view.*

class WorkoutDetailGrandParentAdapter(
    var context: FragmentActivity
) :
    RecyclerView.Adapter<WorkoutDetailGrandParentAdapter.ViewHolder>() {
    private var mData = ArrayList<WorkOutModel>()
    lateinit var recyclerChildAdapter: WorkoutDetailChildAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.workout_details_parent_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData[position]
        holder.itemView.txtTime.text = data.time
        holder.itemView.txtExerciseName.text = data.exerciseName
        if (data.isExpanded) {
            holder.itemView.recyclerChildWorkout.visibility = View.VISIBLE
            //    holder.itemView.updown.setImageResource(R.drawable.ic_up_arrow)
        } else {
            holder.itemView.recyclerChildWorkout.visibility = View.GONE
            // holder.itemView.updown.setImageResource(R.drawable.ic_down_arrow)
        }

        //set the click on Cardview
        holder.itemView.parentView.setOnClickListener {
            mData[position].isExpanded = !data.isExpanded
            notifyDataSetChanged()
        }

        //************************ChildAdapter****************************//
        recyclerChildAdapter =
            WorkoutDetailChildAdapter(context)
        val manager = LinearLayoutManager(context)
        holder.itemView.recyclerChildWorkout.layoutManager = manager //set layout in recycler
        holder.itemView.recyclerChildWorkout.adapter = recyclerChildAdapter
        recyclerChildAdapter.updateData(data.childArray)
    }

    fun upDateData(it: ArrayList<WorkOutModel>) {
        mData = it
        notifyDataSetChanged()

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}
