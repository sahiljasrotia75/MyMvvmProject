package com.app.niluscoach.settingFragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.niluscoach.R
import com.app.niluscoach.register.RegisterActivity
import com.app.niluscoach.signUpScreen.SignUpScreenActivity
import com.app.niluscoach.workOut.workoutDetailsFragment.DividerItemDecorator
import com.example.caiguru.commonScreens.blockedUser.SettingsViewModel
import kotlinx.android.synthetic.main.dialog_logout.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_work_out.*


class SettingsFragment : Fragment(), SettingsAdapter.setClick {


    private lateinit var dialog: Dialog
    private lateinit var mSettingsAdapter: SettingsAdapter
    private lateinit var mVmodel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVmodel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        setAdapter()//set adapter
        setObserver()
        setClick()
    }

    private fun setClick() {
        edtProfile.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            intent.putExtra("editProfileKey", "")
            startActivity(intent)
        }
    }

    private fun setObserver() {
        mVmodel.setSettingsData().observe(viewLifecycleOwner, Observer {
            mSettingsAdapter.updateData(it)
        })
    }

    private fun setAdapter() {
        val manager = LinearLayoutManager(context)
        recyclerSettings.layoutManager = manager
        mSettingsAdapter = SettingsAdapter(activity!!,this)
        recyclerSettings.adapter = mSettingsAdapter

        val dividerItemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecorator(ContextCompat.getDrawable(
                context!!, R.drawable.divider))
        recyclerSettings.addItemDecoration(dividerItemDecoration)

    }

    //*******************adapter click
    override fun adapterClick(position: Int) {
        if (position == 0) {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, "message")
            startActivityForResult(Intent.createChooser(share, getString(R.string.share)), 1)
        }

        if (position == 5) {
            logout()
        }
    }

    fun logout() {
        try {
            dialog = Dialog(activity!!)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.dialog_logout)
            dialog.show()
            //Constant.BACKGROUND_API_HIT=true
            dialog.yes.setOnClickListener {
                val intent = Intent(activity, SignUpScreenActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                activity?.finish()
            }

            dialog.no.setOnClickListener {
                dialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}