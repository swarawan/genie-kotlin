package com.swarawan.geniekotlin.widget

import android.app.ProgressDialog
import android.content.Context

/**
 * Created by rioswarawan on 7/12/17.
 */
class CustomProgressDialog {

    companion object {

        var progressDialog: ProgressDialog? = null

        fun show(context: Context) {
            if (progressDialog != null) {
                progressDialog!!.show()
            } else init(context)
        }

        fun hide() {
            if (progressDialog != null && progressDialog!!.isShowing)
                progressDialog!!.hide()
        }

        fun init(context: Context) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setMessage("Tunggu sebentar...")
        }
    }
}