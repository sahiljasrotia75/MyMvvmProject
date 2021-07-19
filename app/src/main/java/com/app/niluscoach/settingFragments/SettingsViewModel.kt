package com.example.caiguru.commonScreens.blockedUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.homeFragment.HomeModel

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    var mRepo = SettingsRepository(application)


    fun setSettingsData(): MutableLiveData<ArrayList<HomeModel>> {

        return mRepo.setSettingsData()
    }


}
