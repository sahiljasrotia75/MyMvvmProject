package com.app.niluscoach.activitiesFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.homeFragment.HomeModel

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    var mRepo = ActivityRepository(application)


    fun mSucessfulFullWOrkOutData(): MutableLiveData<ArrayList<HomeModel>> {
        return mRepo.mSucessfulFullWOrkOutData()
    }

    fun mSucessfulTopWOrkOutData(): MutableLiveData<ArrayList<HomeModel>> {
        return mRepo.mSucessfulTopWOrkOutData()
    }
}
