package com.example.caiguru.commonScreens.blockedUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.homeFragment.HomeModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var mRepo = HomeRepository(application)


    fun setSuggestedData(): MutableLiveData<ArrayList<HomeModel>> {

        return mRepo.setSuggestedData()
    }

    fun setCategoryData(): MutableLiveData<ArrayList<HomeModel>> {

        return mRepo.setCategoryData()
    }
}
