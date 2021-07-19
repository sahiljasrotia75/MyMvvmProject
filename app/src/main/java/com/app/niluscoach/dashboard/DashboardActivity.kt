package com.app.niluscoach.dashboard

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.app.niluscoach.R
import com.app.niluscoach.activitiesFragment.ActivityFragment
import com.app.niluscoach.homeFragment.HomeFragment
import com.app.niluscoach.settingFragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var mBottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initData()
    }

    private fun initData() {
        //first time fragment Load
        mBottomNavigation = findViewById(R.id.homeBottomNav)
        mBottomNavigation.setOnNavigationItemSelectedListener(this)
        loadFragment(HomeFragment())

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    //****************bottom navigation click listner
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.homeDashBoard -> {
                loadFragment(HomeFragment())
                mBottomNavigation.menu.getItem(0).isCheckable = true
            }
            R.id.activities ->{
                loadFragment(ActivityFragment())
                mBottomNavigation.menu.getItem(1).isChecked = true
            }
            R.id.setting ->{
                loadFragment(SettingsFragment())
                mBottomNavigation.menu.getItem(2).isChecked = true
            }
        }
        return false
    }
}