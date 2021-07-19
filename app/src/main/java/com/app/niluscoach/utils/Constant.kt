package com.app.niluscoach.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object Constant {
    
    var userFirstTime = true
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val token: String = "token"

    /**
     * Permission enable check for camera and Gallery
     */
    fun checkPermissionForCameraGallery(context: Activity): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 102
            )
            return false

        } else if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA

                ), 102
            )
            return false
        } else {
            return true
        }
    }

    /**
     * Shared preference
     */
    fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("Register", Context.MODE_PRIVATE)
    }

    /**
     * Validate email Id format
     */
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target)
            .matches()
    }

    /**
     * Hide keypad
     */
    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun uploadImage(
        bitmap: Bitmap,
        context: Context
    ): File {
        return getImageUriGallery(bitmap, context)
    }

    private fun getImageUriGallery(inImage: Bitmap, context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val externalStorageVolumes =
            ContextCompat.getExternalFilesDirs(context, null)
        val primaryExternalStorage = externalStorageVolumes[0]
        //////////
        val mFile: File
        val fn = primaryExternalStorage.toString() + File.separator + imageFileName + ".jpg"
        mFile = File(fn)

        try {
            val out = FileOutputStream(mFile)
            inImage.compress(Bitmap.CompressFormat.JPEG, 80, out)

            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mFile
    }
}