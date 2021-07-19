package com.example.caiguru.commonScreens.blockedUser

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.workOut.workoutDetailsFragment.WorkOutModel


class WorkOutActivityRepository(var application: Application) {
    var mSucessfulWorkOutData = MutableLiveData<ArrayList<WorkOutModel>>()

    init {
        mSucessfulWorkOutData.value = setExerxiseDetails()

    }


@SuppressLint("NewApi")
fun setExerxiseDetails(): ArrayList<WorkOutModel> {
    var parentArray=ArrayList<WorkOutModel>()
    var parentModel=WorkOutModel()
    parentModel.exerciseName= "Modified Push Ups"
    parentModel.score= "80"
    parentArray.add(parentModel)

    parentModel=WorkOutModel()
    parentModel.exerciseName= "Modified Push Ups"
    parentModel.score= "75"
    parentArray.add(parentModel)

    parentModel=WorkOutModel()
    parentModel.exerciseName= "Modified Push Ups"
    parentModel.score= "75"
    parentArray.add(parentModel)

    parentModel=WorkOutModel()
    parentModel.exerciseName= "Modified Push Ups"
    parentModel.score= "75"
    parentArray.add(parentModel)


return parentArray
}

    fun setWorkOutData(): MutableLiveData<ArrayList<WorkOutModel>> {

        return mSucessfulWorkOutData
    }




}
