package com.app.niluscoach.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import kotlinx.android.synthetic.main.fitness_level_adapter.view.*
import java.util.ArrayList

class FitnessLevelAdapter(var context: Context) :
    RecyclerView.Adapter<FitnessLevelAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var mData = ArrayList<FitnessModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.fitness_level_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]
        holder.itemView.txtFitenessLevel.text = model.fitnessLevel

        if (model.hasselected) {
            holder.itemView.fitnessCheckBox.setImageResource(model.SelectedDrawable)
        } else {
            holder.itemView.fitnessCheckBox.setImageResource(model.UnSelectedDrawable)
        }

        //set the click on the drawable (one selection)
        holder.itemView.setOnClickListener {
            if (model.hasselected) {
                mData[position].hasselected = false
            } else {
                for (item in mData) {
                    item.hasselected = false
                }
                mData[position].hasselected = true
            }
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
