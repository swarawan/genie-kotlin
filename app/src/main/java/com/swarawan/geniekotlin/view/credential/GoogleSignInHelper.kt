package com.swarawan.geniekotlin.view.credential

import android.support.v4.app.FragmentActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.swarawan.geniekotlin.R

/**
 * Created by rioswarawan on 7/12/17.
 */
class GoogleSignInHelper(activity: FragmentActivity, onConnectionFailedListener: GoogleApiClient.OnConnectionFailedListener) {

    var googleApiClient: GoogleApiClient? = null

    companion object {
        val signInResultCode = 256
    }

    init {
        val options: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, onConnectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build()
    }
}