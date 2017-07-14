package com.swarawan.geniekotlin.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.swarawan.geniekotlin.GenieApp
import com.swarawan.geniekotlin.R
import com.swarawan.geniekotlin.database.firebase.data.Note

/**
 * Created by rioswarawan on 7/13/17.
 */
class NoteAdapter(private val notes: List<Note>, private val selectedListener: SelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflator: LayoutInflater = LayoutInflater.from(GenieApp.context)

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val body: NoteHolder = holder as NoteHolder
        body.textTitle.text = notes[position].title
        body.textContent.text = notes[position].content
        body.layoutNote.setOnClickListener { selectedListener.selectOne(position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflator.inflate(R.layout.item_note, parent, false)
        return NoteHolder(view)
    }

    private class NoteHolder(row: View?) : RecyclerView.ViewHolder(row) {
        val textTitle: TextView = row?.findViewById(R.id.text_title) as TextView
        val textContent: TextView = row?.findViewById(R.id.text_content) as TextView
        val layoutNote: LinearLayout = row?.findViewById(R.id.layout_note) as LinearLayout
    }
}