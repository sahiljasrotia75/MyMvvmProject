package com.app.niluscoach.workOut.workOutActivitiesFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.workOut.workoutDetailsFragment.DividerItemDecorator
import com.example.caiguru.commonScreens.blockedUser.WorkOutActivityViewModel
import kotlinx.android.synthetic.main.fragment_work_out.*
import kotlinx.android.synthetic.main.fragment_work_out_details.*

class WorkOutActivitiesFragment : Fragment() {

    private lateinit var mVmodel: WorkOutActivityViewModel
    private lateinit var mActivitiesAdapter: ActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_work_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVmodel = ViewModelProvider(this).get(WorkOutActivityViewModel::class.java)
      setAdapter()
     setObserver()
    }

    private fun setObserver() {

        mVmodel.setWorkOutData().observe(viewLifecycleOwner, Observer {
            mActivitiesAdapter.upDateData(it)
        })
    }

    private fun setAdapter() {
        //**************suggested adapter
        val manager = LinearLayoutManager(context)
        recyclerActivities.layoutManager = manager
        mActivitiesAdapter = ActivitiesAdapter(activity!!)
        recyclerActivities.adapter = mActivitiesAdapter

        val dividerItemDecoration: RecyclerView.ItemDecoration = DividerItemDecorator(ContextCompat.getDrawable(
            context!!, R.drawable.divider))
        recyclerActivities.addItemDecoration(dividerItemDecoration)
    }

}