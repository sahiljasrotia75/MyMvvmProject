package com.app.niluscoach.homeFragment

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import kotlinx.android.synthetic.main.programs_adapter.view.*

class ProgramsAdapter(
    var context: Context

) : RecyclerView.Adapter<ProgramsAdapter.ViewHolder>() {

    var mData = ArrayList<HomeModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.programs_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]

        val mParams =
            LinearLayout.LayoutParams(getScreenWidth() / 2.9.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT)
        holder.itemView.layoutParams = mParams
        holder.itemView.txtProgramName.text = model.workoutName
//        holder.itemView.txtLevel.text = model.UserLvl
//        holder.itemView.txtTime.text = model.time
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
