package com.app.niluscoach.register

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.BitmapDrawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.niluscoach.R
import com.app.niluscoach.R.color.transparent
import com.app.niluscoach.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fitness_goal_adapter.view.*
import kotlinx.android.synthetic.main.open_from_camera_permission.view.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private var arrayFitnessGoal = ArrayList<FitnessModel>()
    private lateinit var mFitnessLevelAdapter: FitnessLevelAdapter
    private lateinit var mFitnessGoalAdapter: FitnessGoalAdapter
    private lateinit var mViewModel: RegisterViewModel
    var check: Int = -1
    private var photoFile: File? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        mViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        setAdapter()
        setObserver()
        clickListener()
        initData()
    }

    private fun initData() {
        if (intent.hasExtra("editProfileKey")) {
            txtUpdatePassword.visibility = View.VISIBLE
            backBtn.visibility = View.VISIBLE
            btnDone.text = getString(R.string.update)
        } else {
            txtUpdatePassword.visibility = View.GONE
            backBtn.visibility = View.GONE
            btnDone.text = getString(R.string.done)
        }
    }

    private fun clickListener() {
        //back btn click
        backBtn.setOnClickListener {
            finish()
        }


        txtUploadProfilePhoto.setOnClickListener {
            if (Constant.checkPermissionForCameraGallery(this)) {
                showDialog()
            }
        }

    }


    private fun setAdapter() {
        val manager = LinearLayoutManager(this)
        rvFitnessLevel.layoutManager = manager
        mFitnessLevelAdapter = FitnessLevelAdapter(this)
        rvFitnessLevel.adapter = mFitnessLevelAdapter

        /* val manager1 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
         // val manager1 = GridLayoutManager(this,2)
         rvFitnessGoal.layoutManager = manager1
         mFitnessGoalAdapter = FitnessGoalAdapter(this)
         rvFitnessGoal.adapter = mFitnessGoalAdapter
 */
    }

    private fun setObserver() {
        mViewModel.getFitnessLevelData().observe(this, Observer {
            if (it.size > 0) {
                mFitnessLevelAdapter.update(it)
            }
        })

        mViewModel.getFitnessGoalData().observe(this, Observer {
            if (it.size > 0) {
                arrayFitnessGoal = it
                //    mFitnessGoalAdapter.update(it)
                chipData(arrayFitnessGoal)
            }
        })
    }

    @SuppressLint("ResourceType")
    private fun chipData(it: ArrayList<FitnessModel>) {
        for (index in it) {
            val chip = Chip(chip_group.context, null, R.style.chipTheme)
            chip.text = index.fitnessGoal
            // necessary to get single selection working
            chip.isClickable = true
            chip.isCheckable = false
            chip_group.isSingleSelection = true
            chip.chipBackgroundColor = resources.getColorStateList(R.color.colorbg)
            chip.chipStrokeColor = resources.getColorStateList(R.color.darkgrey)
            chip.chipIcon = resources.getDrawable(index.imagegrey)
            chip.chipStrokeWidth = 1f
            chip.alpha = 0.5F
            chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
            chip.chipStartPadding = 20.0f
            chip.chipEndPadding = 20.0f
            chip.gravity = Gravity.CENTER

            chip_group.addView(chip)

            chip.setOnClickListener {
                if (index.hasselected) {
                    index.hasselected = false
                    chip.chipStrokeColor = resources.getColorStateList(R.color.darkgrey)
                    chip.chipIcon = resources.getDrawable(index.imagegrey)
                    chip.alpha = 0.5F
                    chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
                } else {
                    index.hasselected = true
                    chip.chipStrokeColor = resources.getColorStateList(R.color.colorRed)
                    chip.chipIcon = resources.getDrawable(index.imagered)
                    chip.alpha = 1F
                    chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
                }
            }

            chip_group.isSingleSelection = true
            chip_group.setOnCheckedChangeListener { chipGroup, i ->
                val chip1 = chipGroup.findViewById<Chip>(i)
                if (chip1 != null) {
                    Toast.makeText(this, "clickk", Toast.LENGTH_LONG).show()
                }
            }
            chip_group.isSingleSelection = true
        }

    }


    private fun showDialog() {
        //....................................Update profile image by gallary and camera.................//
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Open By")
        // Seems ok to inflate view with null rootView
        val view = layoutInflater.inflate(R.layout.open_from_camera_permission, null)
        builder.setView(view)

        if (check == 0) {
            view.camera.isChecked = true
            view.gallery.isChecked = false
        }
        if (check == 1) {
            view.gallery.isChecked = false
            view.camera.isChecked = true
        }


        builder.setPositiveButton("Ok") { dialog, which ->
            if (view.camera.isChecked) {
                openCamera()
                check = 0

            } else if (view.gallery.isChecked) {
                openGallery()
                check = 1
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->

        }
        builder.create()
        builder.show()

    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            photoFile = createImageFile()
        } catch (ex: Exception) {
            // Error occurred while creating the File
            Log.i("", "IOException")
        }

        // Continue only if the File was successfully created
        if (photoFile != null) {
            uri = FileProvider.getUriForFile(
                this,
                "com.app.niluscoach" + ".provider",
                photoFile!!
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intent, 29)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(intent, 30)
    }

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val externalStorageVolumes = ContextCompat.getExternalFilesDirs(this, null)
        val primaryExternalStorage = externalStorageVolumes[0]
        //////////
        val mFile: File
        val fn = primaryExternalStorage.toString() + File.separator + imageFileName + ".jpg"
        mFile = File(fn)
        return mFile
    }


    @SuppressLint("SimpleDateFormat")
    private fun getImageUriCamera(inImage: Bitmap): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val externalStorageVolumes = ContextCompat.getExternalFilesDirs(applicationContext, null)
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

    private fun getRotateImage(photoFile: File): Bitmap {

        val ei: ExifInterface = ExifInterface(photoFile.path)
        val orientation = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
        val bitmap = BitmapFactory.decodeFile(photoFile.path)

        var rotatedBitmap: Bitmap? = bitmap
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 ->
                rotatedBitmap = TransformationUtils.rotateImage(bitmap, 90)

            ExifInterface.ORIENTATION_ROTATE_180 ->
                rotatedBitmap = TransformationUtils.rotateImage(bitmap, 180)

            ExifInterface.ORIENTATION_ROTATE_270 ->
                rotatedBitmap = TransformationUtils.rotateImage(bitmap, 270)

            ExifInterface.ORIENTATION_NORMAL ->
                rotatedBitmap = bitmap
        }

        return rotatedBitmap!!

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //1st Camera Result
        try {
            if (requestCode == 29) {
                try {
                    val resultBitmap = getRotateImage(photoFile!!)
                    val file = getImageUriCamera(resultBitmap)
//..................................Api FOr Update Image....................................................................//
                    //   mViewModel.imgLink(uri)
                    //  progressBar.visibility = android.view.View.VISIBLE
                    Glide.with(this).load(file).into(img)


                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }

            }

            //2nd Gallery Result

            else if (requestCode == 30 && data != null) {

                try {
                    val selectedPicture: Uri? = data.data
                    galleryImage.setImageURI(selectedPicture)
                    galleryImage.invalidate()
                    val drawable: BitmapDrawable = galleryImage.getDrawable() as BitmapDrawable
                    val bitmap: Bitmap = drawable.getBitmap()

                    val file: File = Constant.uploadImage(
                        bitmap,
                        this
                    )
                    bitmap.recycle()
                    //progressBar.visibility = android.view.View.VISIBLE
                    Glide.with(this).load(file)
                        .into(img)


                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}


