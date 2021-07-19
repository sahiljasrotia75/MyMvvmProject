package com.app.niluscoach.forgetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.app.niluscoach.R
import com.app.niluscoach.login.LoginActivity
import com.app.niluscoach.utils.Constant
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_forget_password.edtEmail

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        setToolbar()
        clickListener()

    }

    //.........setuptool bar..............//
    private fun setToolbar() {
        //getting toolbar id
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        title = ""
        setSupportActionBar(toolbar)
        //set the text
        text = findViewById(R.id.title)
        text.text = getText(R.string.forgot_password)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
    }


    private fun clickListener() {
        btnSend.setOnClickListener {
            if (edtEmail.text.trim().isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            } else if (!Constant.isValidEmail(edtEmail.text)) {
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //..........back button click...........//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null) {
            if (item.itemId == android.R.id.home) {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}