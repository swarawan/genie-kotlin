package com.swarawan.geniekotlin.widget

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

/**
 * Created by rioswarawan on 7/14/17.
 */
class CustomDialog {

    companion object {

        fun show(activity: Activity, message: String) {
            val dialog: AlertDialog = AlertDialog.Builder(activity)
                    .setPositiveButton("OK", null)
                    .setMessage(message)
                    .create()
            dialog.show()
        }

        fun show(activity: Activity, message: String, okClickedListener: DialogInterface.OnClickListener) {
            val dialog: AlertDialog = AlertDialog.Builder(activity)
                    .setPositiveButton("OK", okClickedListener)
                    .setMessage(message)
                    .create()
            dialog.show()
        }

        fun show(activity: Activity, message: String, okClickedListener: DialogInterface.OnClickListener, cancelClickedListener: DialogInterface.OnClickListener) {
            val dialog: AlertDialog = AlertDialog.Builder(activity)
                    .setPositiveButton("OK", okClickedListener)
                    .setNegativeButton("TIDAK", cancelClickedListener)
                    .setMessage(message)
                    .create()
            dialog.show()
        }
    }
}