package com.android.snotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.snotes.adapter.NoteAdapter
import com.android.snotes.adapter.NoteOnClickInterface
import com.android.snotes.entity.Note
import com.android.snotes.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteOnClickInterface {

    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var addNotesFab: FloatingActionButton
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteAdapter = NoteAdapter()
        noteAdapter.setContext(this)
        noteAdapter.setNoteOnClickInterface(this)
        val linearLayout = GridLayoutManager(this, 2)
        notesRecyclerView.layoutManager = linearLayout
        notesRecyclerView.adapter = noteAdapter

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, {
            it?.let {
                noteAdapter.updateNotes(it)
            }
        })
    }

    override fun onNoteClick(note: Note) {
        TODO("Not yet implemented")
    }

    override fun onDelete(note: Note) {
        TODO("Not yet implemented")
    }
}