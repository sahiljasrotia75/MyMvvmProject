package com.app.niluscoach.workOut.workOutActivitiesFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.workOut.workoutDetailsFragment.WorkOutModel
import kotlinx.android.synthetic.main.workout_activities_adapter.view.*

class ActivitiesAdapter(
    var context: FragmentActivity
) :
    RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {
    private var mData = ArrayList<WorkOutModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.workout_activities_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData[position]
        holder.itemView.txtWorkOutName.text = data.exerciseName
        holder.itemView.TxtScore.text = data.score
        holder.itemView.txtSrNum.text = (position+1).toString()



    }

    fun upDateData(it: ArrayList<WorkOutModel>) {
        mData = it
        notifyDataSetChanged()

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}
