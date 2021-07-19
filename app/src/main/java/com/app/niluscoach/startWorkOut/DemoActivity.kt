package com.app.niluscoach.startWorkOut

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.PointF
import android.hardware.Camera
import android.media.MediaScannerConnection
import android.os.Bundle
import android.view.Gravity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.app.niluscoach.R
import kotlinx.android.synthetic.main.activity_demo.*
import java.io.File

class DemoActivity : AppCompatActivity() {

    private val neededPermissions = arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE)
    private var preview: SurfaceView? = null
    private var previewHolder: SurfaceHolder? = null
    private var camera: Camera? = null
    private var inPreview = false
    var image: ImageView? = null
    var bmp: Bitmap? = null
    var mutableBitmap: Bitmap? = null
    var start = PointF()
    var imageFileName: File? = null
    var imageFileFolder: File? = null
    private var msConn: MediaScannerConnection? = null
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)


    }
}