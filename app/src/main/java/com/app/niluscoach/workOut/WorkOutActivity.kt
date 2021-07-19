package com.app.niluscoach.workOut

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.niluscoach.R
import com.app.niluscoach.startWorkOut.StartWorkOutActivity
import com.app.niluscoach.workOut.workOutActivitiesFragments.WorkOutActivitiesFragment
import com.app.niluscoach.workOut.workoutDetailsFragment.WorkOutDetailsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_work_out.*


class WorkOutActivity : AppCompatActivity() {

    //  var fragments: ArrayList<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_out)
        setClick()
        initData()
    }


    private fun setClick() {
        backButton.setOnClickListener {
            finish()
        }

        tabLayouts.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    loadFragment(WorkOutDetailsFragment())
                } else {
                    loadFragment(WorkOutActivitiesFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {


            }
        })

        //*******btn click
        btnStartWorkOut.setOnClickListener {
            val intent = Intent(this, StartWorkOutActivity::class.java)
            startActivity(intent)
        }


    }


    private fun initData() {
        //  transparentStatusAndNavigation()
        //set tabs
        tabLayouts.addTab(tabLayouts.newTab().setText("Details"))
        tabLayouts.addTab(tabLayouts.newTab().setText("Your Activities"))

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.workLayoutframe, fragment)
        transaction.commit()
    }
//    private fun hidingStatusBar() {
//        //hiding status bar programitically
//        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
//            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//        if (Build.VERSION.SDK_INT >= 21) {
//            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
//            window.statusBarColor = Color.TRANSPARENT
//        }
//    }
//    private fun setWindowFlag(bits: Int, on: Boolean) {
//        val win = window
//        val winParams = win.attributes
//        if (on) {
//            winParams.flags = winParams.flags or bits
//        } else {
//            winParams.flags = winParams.flags and bits.inv()
//        }
//        win.attributes = winParams
//    }

    private fun transparentStatusAndNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}