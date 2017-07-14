package com.swarawan.geniekotlin.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.swarawan.geniekotlin.R
import com.swarawan.geniekotlin.database.firebase.data.Note
import com.swarawan.geniekotlin.view.add.AddActivity
import com.swarawan.geniekotlin.view.main.presenter.NotePresenter
import com.swarawan.geniekotlin.view.main.presenter.NoteSyncListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by rioswarawan on 7/13/17.
 */
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val adapter: NoteAdapter
    private val notes: MutableList<Note> = mutableListOf()
    private val presenter: NotePresenter

    private val onSelectedListener = object : SelectedListener {
        override fun selectOne(position: Int) {
            val title: String = notes[position].title
            val content: String = notes[position].content

            println("Selected Note: $title - $content")
        }
    }

    private val syncronizeListener = object : NoteSyncListener {
        override fun retrieveNotes(data: List<Note>) {
            addAll(data)
            swipe.isRefreshing = false
        }
    }

    init {
        presenter = NotePresenter(syncronizeListener)
        adapter = NoteAdapter(notes, onSelectedListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_note.adapter = adapter
        recyclerview_note.layoutManager = LinearLayoutManager(this)
        recyclerview_note.setHasFixedSize(true)
        fab.setOnClickListener(onFabClicked)

        presenter.syncronize()
    }

    /**
     * Handle refresh on swiping down screen
     */
    override fun onRefresh() {
        presenter.retrieveOnce()
    }

    private fun addAll(data: List<Note>) {
        notes.clear()
        notes.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private val onFabClicked: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, AddActivity::class.java))
    }
}