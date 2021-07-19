package com.app.niluscoach.signUpScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.app.niluscoach.R
import com.app.niluscoach.login.LoginActivity
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.Config

class SignUpScreenActivity : AppCompatActivity() {
    private lateinit var pagerIndicator: CircleIndicator
    var viewPager: ViewPager? = null
    private var currentPostion: Int = 0
    private lateinit var btnNext: TextView
    var images = intArrayOf(R.mipmap.intro_screen1, R.mipmap.intro_screen2, R.mipmap.intro_screen3)
    var myCustomPagerAdapter: CustomPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        initilization()

        if (viewPager != null) {
            myCustomPagerAdapter = CustomPagerAdapter(supportFragmentManager, images)
            viewPager!!.adapter = myCustomPagerAdapter
        }

        settingIndicators()
        pagerListener()
        clickListener()
    }

    private fun initilization() {
        viewPager = findViewById(R.id.signUpviewPager)
        pagerIndicator = findViewById(R.id.indicator_signUp)
        btnNext = findViewById(R.id.btnNext)
    }

    private fun pagerListener() {
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {/*empty*/
                currentPostion = position
                when (currentPostion) {
                    0 -> btnNext.text = "NEXT"
                    1 -> btnNext.text = "NEXT"
                    else -> btnNext.text = "GET STARTED"
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                when (currentPostion) {
                    0 -> btnNext.text = "NEXT"
                    1 -> btnNext.text = "NEXT"
                    else -> btnNext.text = "GET STARTED"
                }
            }

            override fun onPageScrollStateChanged(state: Int) {/*empty*/
            }
        })

    }

    private fun clickListener() {
        btnNext.setOnClickListener {
            viewPager!!.setCurrentItem(currentPostion + 1, true)
            if (currentPostion == 2) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                //finish()
            }
        }
    }

    private fun settingIndicators() {
        val indicatorWidth = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10f,
            resources.displayMetrics
        ) + 0.5f).toInt()
        val indicatorHeight = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 4f,
            resources.displayMetrics
        ) + 0.5f).toInt()
        val indicatorMargin = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 4f,
            resources.displayMetrics
        ) + 0.5f).toInt()

        val config = Config.Builder().width(indicatorWidth)
            .height(indicatorHeight)
            .margin(indicatorMargin)
//            .animatorReverse(R.animator.indicator_animator_reverse)
            .drawable(R.drawable.black_radius_square)
            .drawableUnselected(R.drawable.viewpager_background_grey)
            .build()
        pagerIndicator.initialize(config)
        viewPager!!.adapter = CustomPagerAdapter(supportFragmentManager, images)
        pagerIndicator.setViewPager(viewPager)
    }

}