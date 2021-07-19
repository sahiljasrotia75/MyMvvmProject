package com.example.caiguru.commonScreens.blockedUser

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.R
import com.app.niluscoach.homeFragment.HomeModel
import com.app.niluscoach.workOut.workoutDetailsFragment.WorkOutModel


class WorkOutRepository(var application: Application) {
    var mSucessfulWorkOutData = MutableLiveData<ArrayList<WorkOutModel>>()

    init {
        mSucessfulWorkOutData.value = setExerxiseDetails()

    }


    @SuppressLint("NewApi", "UseCompatLoadingForDrawables")
    fun setExerxiseDetails(): ArrayList<WorkOutModel> {
        var parentArray = ArrayList<WorkOutModel>()
        var parentModel = WorkOutModel()

        var childArray = ArrayList<HomeModel>()
        var childModel = HomeModel()

        parentModel.time = "0:24"
        parentModel.isExpanded = false
        parentModel.exerciseName = "Modified Push Ups"

        childModel.image = application.getDrawable(R.drawable.gym_image)
        childArray.add(childModel)
        parentModel.childArray = childArray
        parentArray.add(parentModel)

        //2nd
        parentModel = WorkOutModel()
        childModel = HomeModel()
        childArray = ArrayList()

        parentModel.time = "0:24"
        parentModel.isExpanded = false
        parentModel.exerciseName = "Modified Push Ups"

        childModel.image = application.getDrawable(R.drawable.gym_image)
        childArray.add(childModel)
        parentModel.childArray = childArray
        parentArray.add(parentModel)

        //3rd
        parentModel = WorkOutModel()
        childModel = HomeModel()
        childArray = ArrayList()

        parentModel.time = "0:24"
        parentModel.isExpanded = false
        parentModel.exerciseName = "Modified Push Ups"

        childModel.image = application.getDrawable(R.drawable.gym_image)
        childArray.add(childModel)
        parentModel.childArray = childArray
        parentArray.add(parentModel)

        //4th
        parentModel = WorkOutModel()
        childModel = HomeModel()
        childArray = ArrayList()

        parentModel.time = "0:24"
        parentModel.isExpanded = false
        parentModel.exerciseName = "Modified Push Ups"
        childModel.image = application.getDrawable(R.drawable.gym_image)
        childArray.add(childModel)
        parentModel.childArray = childArray
        parentArray.add(parentModel)

        //5dth
        parentModel = WorkOutModel()
        childModel = HomeModel()
        childArray = ArrayList()

        parentModel.time = "0:24"
        parentModel.isExpanded = false
        parentModel.exerciseName = "Modified Push Ups"

        childModel.image = application.getDrawable(R.drawable.gym_image)
        childArray.add(childModel)
        parentModel.childArray = childArray
        parentArray.add(parentModel)


        return parentArray
    }

    fun setWorkOutData(): MutableLiveData<ArrayList<WorkOutModel>> {

        return mSucessfulWorkOutData
    }


}
