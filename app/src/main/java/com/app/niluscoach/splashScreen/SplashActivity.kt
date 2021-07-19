package com.app.niluscoach.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.niluscoach.MainActivity
import com.app.niluscoach.R
import com.app.niluscoach.signUpScreen.SignUpScreenActivity
import com.app.niluscoach.startWorkOut.Accelerometer
import com.app.niluscoach.startWorkOut.DemoActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, mSplashDelay)
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

    private var mDelayHandler: Handler? = null
    private val mSplashDelay: Long = 3000

    private val mRunnable: Runnable = Runnable {
      val intent = Intent(this, SignUpScreenActivity::class.java)//if login
    // val intent = Intent(this, MainActivity::class.java)//if login
        startActivity(intent)
        finish()
    }
}