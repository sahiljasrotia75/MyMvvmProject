package com.app.niluscoach.workOut.workoutDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.app.niluscoach.R
import com.example.caiguru.commonScreens.blockedUser.WorkOutDetailViewModel
import kotlinx.android.synthetic.main.fragment_work_out_details.*


class WorkOutDetailsFragment : Fragment() {
    private lateinit var mVmodel: WorkOutDetailViewModel
    private lateinit var mWorkOutAdapter: WorkoutDetailParentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_work_out_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVmodel = ViewModelProvider(this).get(WorkOutDetailViewModel::class.java)
       setAdapter()
      setObserver()
    }

    private fun setObserver() {
        mVmodel.setWorkOutData().observe(viewLifecycleOwner, Observer {
            mWorkOutAdapter.upDateData(it)
        })
    }

    private fun setAdapter() {
        val manager = LinearLayoutManager(context)
        recyclerWorkOutParent.layoutManager = manager
        mWorkOutAdapter = WorkoutDetailParentAdapter(activity!!)
        recyclerWorkOutParent.adapter = mWorkOutAdapter

        val dividerItemDecoration: ItemDecoration = DividerItemDecorator(ContextCompat.getDrawable(
            context!!, R.drawable.divider))
        recyclerWorkOutParent.addItemDecoration(dividerItemDecoration)
    }
}