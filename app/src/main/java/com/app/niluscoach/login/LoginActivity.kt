package com.app.niluscoach.login

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.niluscoach.R
import com.app.niluscoach.dashboard.DashboardActivity
import com.app.niluscoach.forgetPassword.ForgetPasswordActivity
import com.app.niluscoach.register.RegisterActivity
import com.app.niluscoach.subscriptionScreen.SubscriptionActivity
import com.app.niluscoach.utils.Constant
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import java.net.MalformedURLException
import java.net.URL
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private var callbackManager: CallbackManager? = null

    // private lateinit var googleApiClient: GoogleApiClient
    private val RC_SIGN_IN = 1
    var userEmail: String = ""
    var userName: String = ""
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(
            applicationContext,
            resources.getString(R.string.facebook_app_id)
        )
        callbackManager = CallbackManager.Factory.create();

        try {
            LoginManager.getInstance().logOut()
            Constant.mGoogleSignInClient.signOut()
        } catch (e: java.lang.Exception) {

        }
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        Constant.mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//        googleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this, this)
//            .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        printHashKey()
        clickListener()

    }

    private fun clickListener() {
        txtForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        txtRegisterBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {

            if (edtEmail.text.trim().isEmpty() || editTextTextPassword.text.trim()
                    .isEmpty()
            ) {
                Toast.makeText(this, "Please enter data in all the fields", Toast.LENGTH_SHORT).show()
            } else if (!Constant.isValidEmail(edtEmail.text)) {
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
            } else {
                if (Constant.userFirstTime) {
                    Constant.userFirstTime = false
                    val intent = Intent(this, SubscriptionActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        btnFacebook.setOnClickListener {
            facebookLogin()
        }

        btnGoogle.setOnClickListener {
            googleLogin()
        }


        /*      editTextTextPassword.setOnTouchListener(object : View.OnTouchListener {
                  override fun onTouch(v: View?, event: MotionEvent): Boolean {
                      val DRAWABLE_LEFT = 0
                      val DRAWABLE_TOP = 1
                      val DRAWABLE_RIGHT = 2
                      val DRAWABLE_BOTTOM = 3
                      if (event.getAction() === MotionEvent.ACTION_UP) {
                          if (event.getRawX() >= editTextTextPassword.getRight() - editTextTextPassword.getCompoundDrawables()
                                  .get(DRAWABLE_RIGHT).getBounds().width()
                          ) {
                              //editTextTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                              editTextTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                              return true
                          }
                      }
                      return false
                  }
              })*/
    }

    private fun googleLogin() {
        val signInIntent = Constant.mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    private fun printHashKey() {
        try {
            val info: PackageInfo = packageManager
                .getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i("key", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("key1", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("key2", "printHashKey()", e)
        }
    }

    private fun facebookLogin() {
        //    btnLogIn.text = getString(R.string.please_wait)
        //    btnLogIn.isEnabled = false
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"));
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
                    println("onSuccess")

                    val accessToken = loginResult!!.accessToken
                        .token
                    Log.i("accessToken", accessToken)
                    // btnLogin.text = getString(R.string.please_wait)
                    // btnLogin.isEnabled = false
                    //  mViewModel.facebookLogin(accessToken)
                    // facebookAccessToken = accessToken
                    val request: GraphRequest = GraphRequest.newMeRequest(
                        loginResult.accessToken
                    ) { `object`, response ->
                        Log.i(
                            "LoginActivity",
                            response.toString()
                        )
                        try {
                            val id = `object`.getString("id")
                            try {
                                val profile_pic =
                                    URL("http://graph.facebook.com/$id/picture?type=large")
                                Log.i("profile_pic", profile_pic.toString() + "")
                            } catch (e: MalformedURLException) {
                                e.printStackTrace()
                            }

                            val name = `object`.getString("name")
                            val email = `object`.getString("email")

                            //without api
                            //btnLogIn.text = getString(R.string.please_wait)
                            //btnLogIn.isEnabled = false
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            startActivity(intent)
                            //  finish()


                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    val parameters = Bundle()
                    parameters.putString(
                        "fields",
                        "id,name,email,gender, birthday"
                    )
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {
                    println("onCancel");
                    btnLogIn.text = getString(R.string.please_wait)
                    btnLogIn.isEnabled = false
                }

                override fun onError(exception: FacebookException) {
                    btnLogIn.text = getString(R.string.please_wait)
                    btnLogIn.isEnabled = false
                    System.out.println("onError");
                    Log.v("LoginActivity", exception.localizedMessage);
                }
            })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }

    // Google click...
    private fun handleSignInResult(
        result: Task<GoogleSignInAccount>
    ) {
        val auth = Constant.getPrefs(this).getString(
            Constant.token, "")
        try {
            val account = result.getResult(ApiException::class.java)
            val idToken = account!!.idToken
            val userId = account.id

            userEmail = account.email!!
            userName = account.displayName!!
            //url =
            val photoUrl = account.photoUrl.toString()
            url = photoUrl.replace("s96-c", "s800-c")

            //without api
            //  btnLogIn.text = getString(R.string.please_wait)
            //   btnLogIn.isEnabled = false

            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)

            startActivity(intent)



//            url = photoUrl.substring(0, (photoUrl.length) - 15) + "s400-c/photo.jpg"

//            it is google api

            //   if (intent.hasExtra("hasAuthCode")) {
            //        if (url == "null"){
            //           url = url.replace("null","")
            //  mViewModel.setDetailForGoogle(userId, url, userEmail)
//                    dialog.setMessage("Loading...")
//                    dialog.show()
            //      aviLogin.visibility = View.VISIBLE
            //             }else {
            //      mViewModel.setDetailForGoogle(userId, url, userEmail)
//                    dialog.setMessage("Loading...")
//                    dialog.show()
            //           aviLogin.visibility = View.VISIBLE

//                }
            //      } else {
//                if (status == "true") {
//                    mViewModel.setDetailForGoogle(userId, url, userEmail)
////                    dialog.setMessage("Loading...")
////                    dialog.show()
//                    aviLogin.visibility = View.VISIBLE
//
//                }else{
//                    mViewModel.setDetailForGoogle(userId, url, userEmail)
////                    dialog.setMessage("Loading...")
////                    dialog.show()
//                    aviLogin.visibility = View.VISIBLE
//                }
            //        }

        } catch (e: ApiException) {
            e.printStackTrace()

        }

    }


    override fun onBackPressed() {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }
}