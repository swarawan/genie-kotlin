package com.swarawan.geniekotlin.view.credential

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.swarawan.geniekotlin.R
import com.swarawan.geniekotlin.database.local.GlobalPreference
import com.swarawan.geniekotlin.database.local.Key
import com.swarawan.geniekotlin.view.main.MainActivity
import com.swarawan.geniekotlin.widget.CustomProgressDialog
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    val TAG: String = LoginActivity::class.toString()
    var googleSignInHelper: GoogleSignInHelper? = null
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleSignInHelper = GoogleSignInHelper(this, onConnectionFailedListener)
        sign_in_button.setSize(SignInButton.SIZE_STANDARD)
        sign_in_button.setOnClickListener(signInClicked)
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(onAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(onAuthListener)
    }

    override fun onResume() {
        super.onResume()
        CustomProgressDialog.hide()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GoogleSignInHelper.signInResultCode) {
            val googleSignInResult: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(googleSignInResult)
        }
    }

    private fun handleSignInResult(googleSignInResult: GoogleSignInResult) {
        if (googleSignInResult.isSuccess) {
            val account: GoogleSignInAccount? = googleSignInResult.signInAccount
            val credential: AuthCredential = GoogleAuthProvider.getCredential(account?.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this@LoginActivity) {
                        if (!it.isSuccessful)
                            Log.d(TAG, "firebaseAuthWithGoogle: task not success " + it.exception?.localizedMessage);
                        else
                            navigateHome()
                    }
        }
    }

    private fun navigateHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    val signInClicked: View.OnClickListener = View.OnClickListener {
        val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(googleSignInHelper?.googleApiClient)
        startActivityForResult(signInIntent, GoogleSignInHelper.signInResultCode)
    }

    val onConnectionFailedListener: OnConnectionFailedListener = OnConnectionFailedListener {
        Log.d(TAG, " onConnectionFailed :" + it.errorMessage)
    }

    val onAuthListener: FirebaseAuth.AuthStateListener = FirebaseAuth.AuthStateListener {
        if (it.currentUser != null) {
            GlobalPreference.write(Key.googleUserId, (it.currentUser as FirebaseUser).uid, String::class.java)
            navigateHome()
        }
    }
}
