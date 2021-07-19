package com.app.niluscoach

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekybee.fitnesssdk.lib.CameraActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var requestCode = 100
    var btSquat: Button? = null
    var btLunges: Button? = null
    var btPlank: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btSquat = findViewById(R.id.bt_squat)
        btLunges = findViewById(R.id.bt_lunges)
        btPlank = findViewById(R.id.bt_plank)
        btSquat!!.setOnClickListener(this)
        btLunges!!.setOnClickListener(this)
        btPlank!!.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            val count = data!!.getStringExtra("count")
            if (count != null) {
                Toast.makeText(this, "" + count, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View) {
        var isHorizontalPose = false
        when (v.id) {
            R.id.bt_squat -> CameraActivity.CURRENT_EXCERCISE = 1
            R.id.bt_lunges -> CameraActivity.CURRENT_EXCERCISE = 2
            R.id.bt_plank -> {
                CameraActivity.CURRENT_EXCERCISE = 3
                isHorizontalPose = true
            }
        }
        if (isHorizontalPose) {
            val i = Intent(this@MainActivity, com.geekybee.fitnesssdk.plank.CameraActivity::class.java)
            startActivityForResult(i, requestCode)
        } else {
            val i = Intent(this@MainActivity, CameraActivity::class.java)
            startActivityForResult(i, requestCode)
        }
    }
}
