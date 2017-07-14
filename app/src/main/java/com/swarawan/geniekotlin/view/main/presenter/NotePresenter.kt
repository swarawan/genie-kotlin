package com.swarawan.geniekotlin.view.main.presenter

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.swarawan.geniekotlin.database.firebase.DataManager
import com.swarawan.geniekotlin.database.firebase.Route
import com.swarawan.geniekotlin.database.firebase.data.Note
import com.swarawan.geniekotlin.database.firebase.data.SyncResponse

/**
 * Created by rioswarawan on 7/13/17.
 */
class NotePresenter(val listener: NoteSyncListener) {

    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot?) {
            val syncResponse = snapshot!!.getValue(SyncResponse::class.java)

            if (syncResponse != null) {
                val mutableNotes: MutableList<Note> = mutableListOf()
                syncResponse.notes?.forEach { (_, data) ->
                    val note = Note()
                    note.title = data.title
                    note.content = data.content

                    mutableNotes.add(note)
                }
                listener.retrieveNotes(mutableNotes)
            }
        }

        override fun onCancelled(p0: DatabaseError?) {
        }
    }

    fun syncronize() {
        DataManager.syncronize(Route.notes, valueEventListener)
    }

    fun retrieveOnce() {
        DataManager.retrieve(Route.notes, valueEventListener)
    }
}