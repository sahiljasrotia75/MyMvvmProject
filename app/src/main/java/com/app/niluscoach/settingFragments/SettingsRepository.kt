package com.example.caiguru.commonScreens.blockedUser

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.homeFragment.HomeModel


class SettingsRepository(var application: Application) {
    var mSucessfulSettingsData = MutableLiveData<ArrayList<HomeModel>>()

    init {
        mSucessfulSettingsData.value = getSettingsArray()

    }



    fun getSettingsArray(): ArrayList<HomeModel> {
        val array = ArrayList<HomeModel>()
        var model = HomeModel()
        model.settingCategory = "Share the App"
        array.add(model)

        model = HomeModel()
        model.settingCategory = "About Us"
        array.add(model)

        model = HomeModel()
        model.settingCategory = "Terms of use"
        array.add(model)

        model = HomeModel()
        model.settingCategory = "Contact us / Send Feedback"
        array.add(model)

        model = HomeModel()
        model.settingCategory = "Privacy Policy"
        array.add(model)

        model = HomeModel()
        model.settingCategory = "Logout"
        array.add(model)
        return array

    }

    fun setSettingsData(): MutableLiveData<ArrayList<HomeModel>> {

        return mSucessfulSettingsData
    }




}
