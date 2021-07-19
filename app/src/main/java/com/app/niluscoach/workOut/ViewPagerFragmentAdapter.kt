package com.app.niluscoach.workOut


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*


class ViewPagerFragmentAdapter(fm: FragmentManager, var context: Context, fragments: ArrayList<Fragment>) :
    FragmentPagerAdapter(fm) {
    var fragments: ArrayList<Fragment>
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    init {
        this.fragments = fragments
    }
}