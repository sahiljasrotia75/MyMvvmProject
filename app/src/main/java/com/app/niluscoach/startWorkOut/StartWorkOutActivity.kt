package com.app.niluscoach.startWorkOut

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.*
import android.os.Build
import android.os.Bundle
import android.view.Display
import android.view.Surface
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.niluscoach.MainActivity
import com.app.niluscoach.R
import com.bumptech.glide.Glide
import com.camerakit.CameraKitView
import kotlinx.android.synthetic.main.activity_start_work_out.*
import java.util.*


class StartWorkOutActivity : AppCompatActivity(), SensorEventListener {
    private var imgPausePlay: Boolean = true
    private var imagesound: Boolean = true

    private val neededPermissions = arrayOf(Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)

    //    private var mCamera: Camera? = null
//    private var mPreview: CameraPreview? = null
    private var cameraKitView: CameraKitView? = null

    //*************sensor
    // System sensor manager instance.
    private var mSensorManager: SensorManager? = null

    // Accelerometer and magnetometer sensors, as retrieved from the
    // sensor manager.
    private var mSensorAccelerometer: Sensor? = null
    private var mSensorMagnetometer: Sensor? = null

    // Current data from accelerometer & magnetometer.  The arrays hold values
    // for X, Y, and Z.
    private var mAccelerometerData = FloatArray(3)
    private var mMagnetometerData = FloatArray(3)

    // System display. Need this for determining rotation.
    private var mDisplay: Display? = null

    // Very small values for the accelerometer (on all three axes) should
    // be interpreted as 0. This value is the amount of acceptable
    // non-zero drift.
    private val VALUE_DRIFT = 0.05f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_work_out)
        cameraKitView = findViewById(R.id.cameraKit)
        cameraKitView?.requestPermissions(this)


        permissionListner()
        setclick()
        initSensorData()
    }

    private fun permissionListner() {
        cameraKitView?.setPermissionsListener(object : CameraKitView.PermissionsListener {

            override fun onPermissionsSuccess() {
            }

            override fun onPermissionsFailure() {
            }
        })
    }

    override fun onStart() {
        super.onStart()
        cameraKitView!!.onStart()
    }

    override fun onPause() {
        cameraKitView!!.onPause()
        super.onPause()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraKitView!!.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun initSensorData() {
        // Get accelerometer and magnetometer sensors from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor
        // is not available on the device.

        // Get accelerometer and magnetometer sensors from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor
        // is not available on the device.
        mSensorManager = getSystemService(
            SENSOR_SERVICE) as SensorManager
        mSensorAccelerometer = mSensorManager!!.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER)
        mSensorMagnetometer = mSensorManager!!.getDefaultSensor(
            Sensor.TYPE_MAGNETIC_FIELD)

        // Get the display from the window manager (for rotation).

        // Get the display from the window manager (for rotation).
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        mDisplay = wm.defaultDisplay
    }

    private fun setclick() {
//***********button click
        btnStartWorkOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)//if login
            startActivity(intent)
//            screen1View.visibility = View.GONE
//            screen2View.visibility = View.VISIBLE
        }

        //************sound click
        imgSound.setOnClickListener {
            if (imagesound) {
                imagesound = false
                Glide.with(this)
                    .load(R.drawable.ic_music)
                    .into(imgSound)
            } else {
                imagesound = true
                Glide.with(this)
                    .load(R.drawable.ic_stop_music)
                    .into(imgSound)
            }
        }

        //set cancel click
        txtCancelExit.setOnClickListener {
            finish()
        }

//************image play pause click
        imgPlyPause.setOnClickListener {
            if (imgPausePlay) {
                imgPausePlay = false
                Glide.with(this)
                    .load(R.drawable.ic_pause)
                    .into(imgPlyPause)

                //dummy time set
                txtTotalTime.visibility = View.VISIBLE
                innerLayout1.visibility = View.GONE
                innerLayout2.visibility = View.VISIBLE

            } else {
                imgPausePlay = true
                Glide.with(this)
                    .load(R.drawable.ic_play)
                    .into(imgPlyPause)

                //dummy time set
                txtTotalTime.visibility = View.GONE
                innerLayout1.visibility = View.VISIBLE
                innerLayout2.visibility = View.GONE
            }
        }
    }

    //************Setup camera
//    private fun setUpCamera() {
//        val result = checkPermission()
//        if (result) {
//            cameraKitView = findViewById(R.id.cameraKit)
//            mCamera = getCameraInstance()
//
//            mPreview = mCamera?.let {
//                // Create our Preview view
//                CameraPreview(this, it)
//            }
//
//            // Set the Preview view as the content of our activity.
//            mPreview?.also {
//                val preview: FrameLayout = findViewById(R.id.camera_preview)
//                preview.addView(it)
//            }
//        }
//
//    }

    /** A safe way to get an instance of the Camera object. */
//    fun getCameraInstance(): Camera? {
//        return try {
//            Camera.open(1) // attempt to get a Camera instance
//        } catch (e: Exception) {
//            // Camera is not available (in use or does not exist)
//            null // returns null if camera is unavailable
//        }
//    }


//    //SETUP PERMISSION
//    private fun checkPermission(): Boolean {
//        val currentAPIVersion = Build.VERSION.SDK_INT
//        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
//            val permissionsNotGranted = ArrayList<String>()
//            for (permission in neededPermissions) {
//                if (ContextCompat.checkSelfPermission(this,
//                        permission) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    permissionsNotGranted.add(permission)
//                }
//            }
//            if (permissionsNotGranted.size > 0) {
//                var shouldShowAlert = false
//                for (permission in permissionsNotGranted) {
//                    shouldShowAlert = ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        permission)
//                }
//
//                val arr = arrayOfNulls<String>(permissionsNotGranted.size)
//                val permissions = permissionsNotGranted.toArray(arr)
//                if (shouldShowAlert) {
//                    showPermissionAlert(permissions)
//                } else {
//                    requestPermissions(permissions)
//                }
//                return false
//            }
//        }
//        return true
//    }
//
//    private fun showPermissionAlert(permissions: Array<String?>) {
//        val alertBuilder = AlertDialog.Builder(this)
//        alertBuilder.setCancelable(true)
//        alertBuilder.setTitle(R.string.permission_required)
//        alertBuilder.setMessage(R.string.permission_message)
//        alertBuilder.setPositiveButton(android.R.string.yes) { _, _ ->
//            requestPermissions(
//                permissions)
//        }
//        val alert = alertBuilder.create()
//        alert.show()
//    }
//
//    private fun requestPermissions(permissions: Array<String?>) {
//        ActivityCompat.requestPermissions(this@StartWorkOutActivity, permissions, REQUEST_CODE)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//
//        when (requestCode) {
//            REQUEST_CODE -> {
//                for (result in grantResults) {
//                    if (result == PackageManager.PERMISSION_DENIED) {
//                        // Not all permissions granted. Show some message and return.
//                        return
//                    }
//                }
//                setUpCamera()
//                // All permissions are granted. Do the work accordingly.
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

    //*************************************************sensors**********************//

    override fun onResume() {
        super.onResume()
        cameraKitView!!.onResume()


        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onStop().
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL).


        if (mSensorAccelerometer != null) {
            mSensorManager!!.registerListener(this, mSensorAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (mSensorMagnetometer != null) {
            mSensorManager!!.registerListener(this, mSensorMagnetometer,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }


    override fun onStop() {
        cameraKitView!!.onStop()
        super.onStop()

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is stopped.
        mSensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        // The sensor type (as defined in the Sensor class).
        val sensorType = sensorEvent.sensor.type
        when (sensorType) {
            Sensor.TYPE_ACCELEROMETER -> mAccelerometerData = sensorEvent.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> mMagnetometerData = sensorEvent.values.clone()
            else -> return
        }
        // Compute the rotation matrix: merges and translates the data
        // from the accelerometer and magnetometer, in the device coordinate
        // system, into a matrix in the world's coordinate system.
        //
        // The second argument is an inclination matrix, which isn't
        // used in this example.
        val rotationMatrix = FloatArray(9)
        val rotationOK = SensorManager.getRotationMatrix(rotationMatrix,
            null, mAccelerometerData, mMagnetometerData)

        // Remap the matrix based on current device/activity rotation.
        var rotationMatrixAdjusted = FloatArray(9)
        when (mDisplay!!.rotation) {
            Surface.ROTATION_0 -> rotationMatrixAdjusted = rotationMatrix.clone()
            Surface.ROTATION_90 -> SensorManager.remapCoordinateSystem(rotationMatrix,
                SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X,
                rotationMatrixAdjusted)
            Surface.ROTATION_180 -> SensorManager.remapCoordinateSystem(rotationMatrix,
                SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y,
                rotationMatrixAdjusted)
            Surface.ROTATION_270 -> SensorManager.remapCoordinateSystem(rotationMatrix,
                SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X,
                rotationMatrixAdjusted)
        }

        // Get the orientation of the device (azimuth, pitch, roll) based
        // on the rotation matrix. Output units are radians.
        val orientationValues = FloatArray(3)
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrixAdjusted,
                orientationValues)
        }

        // Pull out the individual values from the array.
        val azimuth = orientationValues[0]
        var pitch = orientationValues[1]
        var roll = orientationValues[2]

        // Pitch and roll values that are close to but not 0 cause the
        // animation to flash a lot. Adjust pitch and roll to 0 for very
        // small values (as defined by VALUE_DRIFT).
        if (Math.abs(pitch) < VALUE_DRIFT) {
            pitch = 0f
        }
        if (Math.abs(roll) < VALUE_DRIFT) {
            roll = 0f
        }

        // Reset all spot values to 0. Without this animation artifacts can
        // happen with fast tilts.
        imgSetupAngle1!!.alpha = 0f
        imgSetupAngle2!!.alpha = 0f
        imgSetupAngle3!!.alpha = 0f
        imgSetupAngle4!!.alpha = 0f
        imgSetupAngle5!!.alpha = 0f
        imgSetupAngle6!!.alpha = 0f
        imgSetupAngle7!!.alpha = 0f
        imgSetupAngle8!!.alpha = 0f
        imgSetupAngle9!!.alpha = 0f
        imgSetupAngle10!!.alpha = 0f
        imgSetupAngle11!!.alpha = 0f
        imgSetupAngle12!!.alpha = 0f
        imgSetupAngle13!!.alpha = 0f

        // Set spot color (alpha/opacity) equal to pitch/roll.
        // this is not a precise grade (pitch/roll can be greater than 1)
        // but it's close enough for the animation effect.
        if (pitch <= -0.90 && roll < -2.97) {
            imgSetupAngle1.alpha = 1.0f
        } else if (pitch <= -1.100 && roll < -2.50) {
            imgSetupAngle2.alpha = 1.0f
        } else if (pitch <= -1.110 && roll < -2.50) {
            imgSetupAngle3.alpha = 1.0f
        } else if (pitch <= -1.120 && roll < -2.50) {
            imgSetupAngle4.alpha = 1.0f
        } else if (pitch <= -1.130 && roll < -2.50) {
            imgSetupAngle5.alpha = 1.0f
        } else if (pitch <= -1.140 && roll < -3.2) {
            imgSetupAngle6.alpha = 1.0f
        } else if (pitch <= -1.156 && roll < -3.2) {
            imgSetupAngle8.alpha = 1.0f
        }
        //**************************below center code***********************//
        else if (pitch <= -1.140 && roll > -3.2) {
            imgSetupAngle7.alpha = 1.0f
        } else if (pitch <= -1.130 && roll > -2.50) {
            imgSetupAngle9.alpha = 1.0f
        } else if (pitch <= -1.134 && roll > -2.50) {
            imgSetupAngle10.alpha = 1.0f
        } else if (pitch <= -1.120 && roll > -2.50) {
            imgSetupAngle11.alpha = 1.0f
        } else if (pitch <= -1.110 && roll > -2.50) {
            imgSetupAngle12.alpha = 1.0f
        } else if (pitch <= -0.90 && roll > -2.50) {
            imgSetupAngle13.alpha = 1.0f
        } else {
            imgSetupAngle1.alpha = 1.0f
        }

    }

    /**
     * Must be implemented to satisfy the SensorEventListener interface;
     * unused in this app.
     */
    override fun onAccuracyChanged(sensor: Sensor?, i: Int) {

    }

    companion object {
        const val REQUEST_CODE = 100
    }


}