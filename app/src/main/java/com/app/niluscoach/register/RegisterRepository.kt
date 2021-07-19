package com.app.niluscoach.register

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.niluscoach.R

class RegisterRepository(var application: Application) {


    private val mFitnessModel = MutableLiveData<ArrayList<FitnessModel>>()
    private val mFitnessGoal = MutableLiveData<ArrayList<FitnessModel>>()

    init {
        mFitnessModel.value = getFitnessLevel()
        mFitnessGoal.value= getFitnessGoal()
    }

    private fun getFitnessGoal(): ArrayList<FitnessModel> {
        // creating Arraylist
        val arrayList = ArrayList<FitnessModel>()
        // creating object
        var fitness = FitnessModel()
        fitness.fitnessGoal = "Lose Weight"
        fitness.imagegrey = R.drawable.ic_lose_weight_grey
        fitness.imagered = R.drawable.ic_lose_weight_red
        arrayList.add(fitness)

        fitness = FitnessModel()
        fitness.fitnessGoal = "Build Muscle"
        fitness.imagegrey = R.drawable.ic_build_muscle_grey
        fitness.imagered = R.drawable.ic_build_muscle_red
        arrayList.add(fitness)

        fitness = FitnessModel()
        fitness.fitnessGoal = "Increase Endurance"
        fitness.imagegrey = R.drawable.ic_increase_endurance_grey
        fitness.imagered = R.drawable.ic_increase_endurance_red
        arrayList.add(fitness)

        fitness = FitnessModel()
        fitness.fitnessGoal = "Reduce Stress"
        fitness.imagegrey = R.drawable.ic_reduce_stress_grey
        fitness.imagered = R.drawable.ic_reduce_stress_red
        arrayList.add(fitness)

        return arrayList
    }

    private fun getFitnessLevel(): ArrayList<FitnessModel> {
        // creating Arraylist
        val arrayList = ArrayList<FitnessModel>()
        // creating object
        var fitness = FitnessModel()
        fitness.fitnessLevel = "I'm new to fitness"
        arrayList.add(fitness)


        fitness = FitnessModel()
        fitness.fitnessLevel = "I exercise occasionally"
        arrayList.add(fitness)

        fitness = FitnessModel()
        fitness.fitnessLevel = "I exercise regularly"
        arrayList.add(fitness)

        fitness = FitnessModel()
        fitness.fitnessLevel = "I'm a seasoned athlete"
        arrayList.add(fitness)

        return arrayList
    }

    fun getFitnessData(): MutableLiveData<ArrayList<FitnessModel>> {
        return mFitnessModel
    }

    fun getFitnessGoalData(): MutableLiveData<ArrayList<FitnessModel>> {
        return mFitnessGoal
    }
}
