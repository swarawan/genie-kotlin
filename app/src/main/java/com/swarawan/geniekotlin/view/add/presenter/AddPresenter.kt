package com.swarawan.geniekotlin.view.add.presenter

import com.swarawan.geniekotlin.database.firebase.DataManager
import com.swarawan.geniekotlin.database.firebase.Route
import com.swarawan.geniekotlin.database.firebase.data.Note

/**
 * Created by rioswarawan on 7/14/17.
 */
class AddPresenter(private val listener: AddFormListener) {

    fun validateForm(note: Note) {
        if (note.title.isEmpty())
            listener.validateFailed("Judul tidak boleh kosong")
        else if (note.content.isEmpty())
            listener.validateFailed("Konten tidak boleh kosong")
        else addNote(note)
    }

    fun addNote(note: Note) {
        val value: HashMap<String, Any> = HashMap()
        value.put("title", note.title)
        value.put("content", note.content)

        DataManager.addOrUpdate(Route.addNotes, value)
        listener.successfullySaved()
    }
}