package com.app.niluscoach.signUpScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class CustomPagerAdapter(var context: FragmentManager,var  mData: IntArray) : FragmentPagerAdapter(context) {

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FirstIntroScreenFragment()
            1 -> fragment = SecondIntroScreenFragment()
            2 -> fragment = ThirdIntroScreenActivity()
        }
        return fragment!!
    }
}
