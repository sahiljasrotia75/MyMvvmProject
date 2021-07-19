package com.example.caiguru.commonScreens.blockedUser

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.homeFragment.HomeModel


class HomeRepository(var application: Application) {
    var mSucessfulSuggestedData = MutableLiveData<ArrayList<HomeModel>>()
    var mSucessfulCategoryData = MutableLiveData<ArrayList<HomeModel>>()

    init {
        mSucessfulSuggestedData.value = setSuggestedArray()
        mSucessfulCategoryData.value = setCategoryArray()

    }

    fun setSuggestedArray(): ArrayList<HomeModel> {
        var array = ArrayList<HomeModel>()
        var model = HomeModel()
        model.workoutName = "Program Name 1"
        model.UserLvl = "Intermediate"
        model.time = "10mins"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Program Name 2"
        model.UserLvl = "Beginner"
        model.time = "10mins"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Program Name 3"
        model.UserLvl = "Intermediate"
        model.time = "10mins"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Program Name 4"
        model.UserLvl = "Beginner"
        model.time = "10mins"
        array.add(model)

        model = HomeModel()
        model.workoutName = "Program Name 5"
        model.UserLvl = "Beginner"
        model.time = "20mins"
        array.add(model)

        return array


    }

    fun setCategoryArray(): ArrayList<HomeModel> {
        val array = ArrayList<HomeModel>()
        var model = HomeModel()
        model.workOutCategory = "Fusion Yoga"
        array.add(model)

        model = HomeModel()
        model.workOutCategory = "Meditation"
        array.add(model)

        model = HomeModel()
        model.workOutCategory = "Fusion Yoga"
        array.add(model)

        model = HomeModel()
        model.workOutCategory = "Meditation"
        array.add(model)

        model = HomeModel()
        model.workOutCategory = "Fusion Yoga"
        array.add(model)
        return array

    }

    fun setSuggestedData(): MutableLiveData<ArrayList<HomeModel>> {

        return mSucessfulSuggestedData
    }

    fun setCategoryData(): MutableLiveData<ArrayList<HomeModel>> {

        return mSucessfulCategoryData
    }


}
