package com.app.niluscoach.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.ArrayList

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    val repo = RegisterRepository(application)

    fun getFitnessLevelData(): LiveData<ArrayList<FitnessModel>> {
        return repo.getFitnessData()
    }

    fun getFitnessGoalData(): LiveData<ArrayList<FitnessModel>> {
        return repo.getFitnessGoalData()
    }

}
