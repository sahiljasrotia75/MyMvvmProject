package com.app.niluscoach.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import kotlinx.android.synthetic.main.fitness_goal_adapter.view.*
import java.util.ArrayList

class FitnessGoalAdapter(var context: Context) :
    RecyclerView.Adapter<FitnessGoalAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var mData = ArrayList<FitnessModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.fitness_goal_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]
        holder.itemView.fitnessGoalName.text = model.fitnessGoal
        holder.itemView.selectedImage.setImageResource(model.imagegrey)

        if (model.hasselected) {
            holder.itemView.layoutClick.setBackgroundResource(model.redbackground)
            holder.itemView.selectedImage.setImageResource(model.imagered)
            holder.itemView.fitnessGoalName.alpha = 1F
            holder.itemView.fitnessGoalName.setTextColor(context.resources.getColor(R.color.darkgrey))//set text color
        } else {
            holder.itemView.layoutClick.setBackgroundResource(model.greybackground)
            holder.itemView.fitnessGoalName.setTextColor(context.resources.getColor(R.color.darkgrey))
            holder.itemView.fitnessGoalName.alpha = 0.5F
            holder.itemView.selectedImage.setImageResource(model.imagegrey)
        }

        //set click (multiple Selection)
        holder.itemView.setOnClickListener {
            mData[position].hasselected = !mData[position].hasselected
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun update(it: ArrayList<FitnessModel>) {
        mData = it
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
