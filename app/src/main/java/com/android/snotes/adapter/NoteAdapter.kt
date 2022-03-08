package com.android.snotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.snotes.R
import com.android.snotes.entity.Note
import kotlinx.android.synthetic.main.each_note_item.view.*
import java.util.*


class NoteAdapter: RecyclerView.Adapter<NoteViewHolder>() {
    private var notes = mutableListOf<Note>()
    private lateinit var context: Context
    private lateinit var noteOnClickInterface: NoteOnClickInterface

    fun setContext(context: Context) {
        this.context = context
    }

    fun updateNotes(notes: List<Note>){
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    fun setNoteOnClickInterface(noteOnClickInterface: NoteOnClickInterface){
        this.noteOnClickInterface = noteOnClickInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if(notes.size > 0) {
            val item = notes[position]

            holder.itemView.note_title.text = item.noteTitle
            holder.itemView.note_description.text = item.noteDescription

            holder.itemView.note_delete.setOnClickListener {
                noteOnClickInterface?.onDelete(item)
            }

            holder.itemView.note_container.setOnClickListener {
                noteOnClickInterface?.onNoteClick(item, position)
            }

            val androidColors: IntArray = context!!.resources.getIntArray(R.array.noteCardBackground)
            val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
            holder.itemView.note_container.setBackgroundColor(randomAndroidColor)
        }
    }

    override fun getItemCount(): Int {
        return notes?.size
    }
}