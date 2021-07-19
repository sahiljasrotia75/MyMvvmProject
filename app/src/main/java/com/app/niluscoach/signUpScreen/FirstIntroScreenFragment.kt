package com.app.niluscoach.signUpScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.app.niluscoach.R
import com.bumptech.glide.Glide

class FirstIntroScreenFragment() : Fragment() {
    lateinit var mView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val img_pager = mView.findViewById(R.id.img_pager) as ImageView
        Glide.with(activity!!).load(R.mipmap.intro_screen1).into(img_pager)

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}