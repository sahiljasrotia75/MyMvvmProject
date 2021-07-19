package com.app.niluscoach.activitiesFragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.homeFragment.HomeModel

class ActivityRepository(var application: Application) {
    var mSucessFullWorkOut = MutableLiveData<ArrayList<HomeModel>>()
    var mSucessTopExercise = MutableLiveData<ArrayList<HomeModel>>()

    init {
        mSucessFullWorkOut.value = setFullWorkOutArray()
        mSucessTopExercise.value = setTopExerciseArray()

    }

    fun setFullWorkOutArray(): ArrayList<HomeModel> {
        val array = ArrayList<HomeModel>()
        var model = HomeModel()

        model.workoutName = "Cardio"
        model.time = "1 workout"
        model.UserLvl = "1"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Meditation"
        model.time = "0 workout"
        model.UserLvl = "0"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Fusion Yoga"
        model.time = "1 workout"
        model.UserLvl = "1"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Fusion Yoga"
        model.time = "1 workout"
        model.UserLvl = "0"
        array.add(model)


        return array
    }

    fun setTopExerciseArray(): ArrayList<HomeModel> {
        val array = ArrayList<HomeModel>()
        var model = HomeModel()

        model.workoutName = "Modified Push Ups"
        model.time = "0 "
        array.add(model)

        model = HomeModel()
        model.workoutName = "Jumpings Jacks"
        model.time = "0"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Modified Push Ups"
        model.time = "0 "
        array.add(model)

        model = HomeModel()
        model.workoutName = "Jumpings Jacks"
        model.time = "0 "
        array.add(model)


        return array
    }


    fun mSucessfulFullWOrkOutData(): MutableLiveData<ArrayList<HomeModel>> {
        return mSucessFullWorkOut
    }

    fun mSucessfulTopWOrkOutData(): MutableLiveData<ArrayList<HomeModel>> {
        return mSucessTopExercise
    }
}
