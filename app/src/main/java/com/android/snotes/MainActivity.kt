package com.android.snotes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.snotes.adapter.NoteAdapter
import com.android.snotes.adapter.NoteOnClickInterface
import com.android.snotes.entity.Note
import com.android.snotes.util.Util
import com.android.snotes.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.notes_toolbar

class MainActivity : AppCompatActivity(), NoteOnClickInterface {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(notes_toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.notes_title)
        }

        noteAdapter = NoteAdapter()
        noteAdapter.setContext(this)
        noteAdapter.setNoteOnClickInterface(this)
        val linearLayout = GridLayoutManager(this, 2)
        notes_recyclerview.layoutManager = linearLayout
        notes_recyclerview.adapter = noteAdapter

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes(this)?.observe(this, {
            it?.let {
                noteAdapter.updateNotes(it)
            }
        })

        add_note_button.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNoteClick(note: Note, position: Int) {
        val intent = Intent(this@MainActivity, EditNoteActvity::class.java)
        intent.putExtra("noteId", note.id)
        intent.putExtra("notePosition", position)
        startActivity(intent)
    }

    override fun onDelete(note: Note) {
        noteViewModel.deleteNote(this, note)
        Util.showMessage(this.window.decorView.rootView, "${note.noteTitle} deleted successfully")
    }
}