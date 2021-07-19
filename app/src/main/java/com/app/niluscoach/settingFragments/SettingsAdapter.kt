package com.app.niluscoach.settingFragments

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.homeFragment.HomeModel
import kotlinx.android.synthetic.main.settings_adapter_layout.view.*


class SettingsAdapter(
    var context: Context,var clickContext:setClick


) : RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {


    var child = ArrayList<HomeModel>()
  //  var click = clickContext as setClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.settings_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return child.size

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = child[position]
        holder.itemView.txtName.text = list.settingCategory
        holder.itemView.setOnClickListener {
            clickContext.adapterClick(position)
        }
    }

    fun updateData(childArray: ArrayList<HomeModel>) {
        child = childArray
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface setClick {
        fun adapterClick(position: Int)
    }


}
