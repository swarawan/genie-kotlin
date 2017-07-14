package com.swarawan.geniekotlin.view.main.presenter

import com.swarawan.geniekotlin.database.firebase.data.Note

/**
 * Created by rioswarawan on 7/13/17.
 */
interface NoteSyncListener {

    fun retrieveNotes(data: List<Note>)
}