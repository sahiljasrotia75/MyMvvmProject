package com.app.niluscoach.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.niluscoach.R
import com.example.caiguru.commonScreens.blockedUser.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var mProgramsAdapter: ProgramsAdapter
    private lateinit var mCategoriesAdapter: CategoriesAdapter
    private lateinit var mSuggestedAdapter: SuggestedWorkoutAdapter
    private lateinit var mVmodel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        initData()
        setAdapter()//set adapters
    }

    private fun initData() {
        mVmodel.setSuggestedData().observe(viewLifecycleOwner, Observer {
            mSuggestedAdapter.update(it)
            mProgramsAdapter.update(it)

        })

        mVmodel.setCategoryData().observe(viewLifecycleOwner, Observer {
            mCategoriesAdapter.update(it)

        })
    }

    private fun setAdapter() {

        //**************suggested adapter
        val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerSuggestedWork.layoutManager = manager
        mSuggestedAdapter = SuggestedWorkoutAdapter(activity!!)
        recyclerSuggestedWork.adapter = mSuggestedAdapter

        //***********************categories Adapter
        val manager2 =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerCategories.layoutManager = manager2
        mCategoriesAdapter = CategoriesAdapter(activity!!)
        recyclerCategories.adapter = mCategoriesAdapter

        //*****************programs Adapter
        //***********************categories Adapter
        val manager3 =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerPrograms.layoutManager = manager3
        mProgramsAdapter = ProgramsAdapter(activity!!)
        recyclerPrograms.adapter = mProgramsAdapter

    }

}