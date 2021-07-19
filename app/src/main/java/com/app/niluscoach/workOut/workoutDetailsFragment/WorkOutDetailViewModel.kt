package com.example.caiguru.commonScreens.blockedUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.workOut.workoutDetailsFragment.WorkOutModel

class WorkOutDetailViewModel(application: Application) : AndroidViewModel(application) {
    var mRepo = WorkOutRepository(application)


    fun setWorkOutData(): MutableLiveData<ArrayList<WorkOutModel>> {

        return mRepo.setWorkOutData()
    }

}
