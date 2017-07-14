package com.swarawan.geniekotlin.view.add

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.swarawan.geniekotlin.R
import com.swarawan.geniekotlin.database.firebase.data.Note
import com.swarawan.geniekotlin.view.add.presenter.AddFormListener
import com.swarawan.geniekotlin.view.add.presenter.AddPresenter
import com.swarawan.geniekotlin.widget.CustomDialog.Companion.show
import kotlinx.android.synthetic.main.activity_add.*

/**
 * Created by rioswarawan on 7/14/17.
 */
class AddActivity : AppCompatActivity() {

    private val presenter: AddPresenter

    private val addFormListener = object : AddFormListener {
        override fun validateFailed(message: String) {
            show(this@AddActivity, message)
        }

        override fun successfullySaved() {
            show(this@AddActivity, "Catatan berhasil disimpan", object : OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    finish()
                }
            })
        }
    }

    init {
        presenter = AddPresenter(addFormListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val menuId = item?.itemId
        if (menuId == R.id.action_menu_send)
            saveOrUpdateNote()
        return false
    }

    private fun saveOrUpdateNote() {
        val note: Note = Note()
        note.title = form_title.text.toString()
        note.content = form_content.text.toString()

        presenter.validateForm(note)
    }

}