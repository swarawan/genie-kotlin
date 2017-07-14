package com.swarawan.geniekotlin.view.add.presenter

/**
 * Created by rioswarawan on 7/14/17.
 */
interface AddFormListener {
    fun validateFailed(message: String)

    fun successfullySaved()
}